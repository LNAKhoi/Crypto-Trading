package com.tradingsignal.cryptotrading.services;

import com.tradingsignal.cryptotrading.dtos.TradeInputDto;
import com.tradingsignal.cryptotrading.entities.Trade;

public interface TradeService {
    Trade excecuteTrade(TradeInputDto tradeInputDto);
}
