package com.tradingsignal.cryptotrading.services.impl;

import com.tradingsignal.cryptotrading.dtos.TradeInputDto;
import com.tradingsignal.cryptotrading.entities.Trade;
import com.tradingsignal.cryptotrading.enums.Currency;
import com.tradingsignal.cryptotrading.enums.TradeSide;
import com.tradingsignal.cryptotrading.repositories.PriceRepository;
import com.tradingsignal.cryptotrading.repositories.TradeRepository;
import com.tradingsignal.cryptotrading.repositories.WalletRepository;
import com.tradingsignal.cryptotrading.services.TradeService;
import com.tradingsignal.cryptotrading.utils.SymbolUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
    private final PriceRepository priceRepository;
    private final WalletRepository walletRepository;
    private final TradeRepository tradeRepository;

    @Override
    public Trade excecuteTrade(TradeInputDto tradeInputDto) {
        var price = priceRepository.findPriceBySymbol(tradeInputDto.getSymbol()).orElseThrow();
        var currency = SymbolUtils.getPriceSymbol(tradeInputDto.getSymbol());
        var tradeSide = SymbolUtils.getSide(tradeInputDto.getSide());
        if (tradeSide == null || currency == null) {
            throw new RuntimeException("Trade input is invalid");
        }

        var cryptoWallet = walletRepository.findWalletByUserIdAndCurrency(tradeInputDto.getUserId(),
                currency.toUpperCase()).orElseThrow();
        var usdtWallet = walletRepository.findWalletByUserIdAndCurrency(tradeInputDto.getUserId(),
                Currency.USDT.name()).orElseThrow();

        var quantity = tradeInputDto.getQuantity();
        var tradePrice = tradeSide == TradeSide.BUY ? price.getAsk() : price.getBid();
        var total = tradePrice.multiply(quantity);
        switch (tradeSide) {
            case BUY: {
                var insufficient = usdtWallet.getBalance().compareTo(total) < 0;
                if (!insufficient) {
                    usdtWallet.setBalance(usdtWallet.getBalance().subtract(total));
                    cryptoWallet.setBalance(cryptoWallet.getBalance().add(quantity));
                }
            }
            case SELL: {
                var insufficient = cryptoWallet.getBalance().compareTo(quantity) < 0;
                if (!insufficient) {
                    cryptoWallet.setBalance(cryptoWallet.getBalance().subtract(quantity));
                    usdtWallet.setBalance(usdtWallet.getBalance().add(total));
                }
            }
        }
        walletRepository.save(usdtWallet);
        walletRepository.save(cryptoWallet);
        var trade = createNewTrade(tradeInputDto, tradePrice, total);
        return tradeRepository.save(trade);
    }

    private Trade createNewTrade(TradeInputDto tradeInputDto, BigDecimal tradePrice, BigDecimal total) {
        Trade trade = new Trade();
        trade.setUserId(tradeInputDto.getUserId());
        trade.setSymbol(tradeInputDto.getSymbol());
        trade.setSide(tradeInputDto.getSide());
        trade.setPrice(tradePrice);
        trade.setQuantity(tradeInputDto.getQuantity());
        trade.setTotal(total);
        trade.setTimestamp(LocalDateTime.now());
        return trade;
    }
}
