package com.tradingsignal.cryptotrading.services.impl;

import com.tradingsignal.cryptotrading.services.PriceAggregationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceAggregationServiceImpl implements PriceAggregationService {
    @Value("${binance.url}")
    private String binanceUrl;
    @Value("${houbi.url}")
    private String houbiUrl;

    @Override
    public void aggregatePrices() {

    }
}
