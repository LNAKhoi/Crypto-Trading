package com.tradingsignal.cryptotrading.services.impl;

import com.tradingsignal.cryptotrading.dtos.PriceMessage;
import com.tradingsignal.cryptotrading.enums.Symbol;
import com.tradingsignal.cryptotrading.services.PriceAggregationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PriceAggregationServiceImpl implements PriceAggregationService {
    @Value("${binance.url}")
    private String binanceUrl;
    @Value("${houbi.url}")
    private String houbiUrl;
    @Value("${kafka.price.topic}")
    private String topic;

    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, PriceMessage> kafkaTemplate;

    @Override
    public void aggregatePrices() {
        for (Symbol symbol : Symbol.values()) {
            /** Flow should be:
             * 1: fetch Binance price & Houbi
             * 2: compare 2 value of bid => take the highest one for sell
             * 3: compare 2 value of ask => take the lowest one for buy
             * 4: push to topic for consumer to save
             **/
        }
    }
}
