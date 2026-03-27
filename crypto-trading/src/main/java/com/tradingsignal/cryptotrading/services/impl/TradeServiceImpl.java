package com.tradingsignal.cryptotrading.services.impl;

import com.tradingsignal.cryptotrading.dtos.TradeInputDto;
import com.tradingsignal.cryptotrading.entities.Trade;
import com.tradingsignal.cryptotrading.services.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
    @Override
    public Trade excecuteTrade(TradeInputDto tradeInputDto) {
        return null;
    }
}
