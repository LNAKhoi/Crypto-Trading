package com.tradingsignal.cryptotrading.services.impl;

import com.tradingsignal.cryptotrading.entities.Trade;
import com.tradingsignal.cryptotrading.repositories.TradeRepository;
import com.tradingsignal.cryptotrading.services.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TradeRepository tradeRepository;

    @Override
    public List<Trade> getUserTransactionsHistory(Long id) {
        return tradeRepository.getTradesByUserId(id);
    }
}
