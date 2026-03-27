package com.tradingsignal.cryptotrading.kafka;

import com.tradingsignal.cryptotrading.dtos.PriceMessage;
import com.tradingsignal.cryptotrading.entities.Price;
import com.tradingsignal.cryptotrading.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PriceConsumer {
    private final PriceRepository priceRepository;

    @KafkaListener(topics = "${kafka.price.topic}", groupId = "${crypto-price-group}")
    public void consume(PriceMessage priceMessage) {
        var price = priceRepository.findPriceBySymbol(priceMessage.getSymbol()).orElseGet(Price::new);
        price.setSymbol(priceMessage.getSymbol());
        price.setBid(priceMessage.getBid());
        price.setAsk(priceMessage.getAsk());
        priceRepository.save(price);
        log.info("Price saved with bid: {}, ask: {}", price.getBid(), price.getAsk());
    }
}
