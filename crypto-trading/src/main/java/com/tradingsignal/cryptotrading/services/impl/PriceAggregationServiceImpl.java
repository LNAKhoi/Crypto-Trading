package com.tradingsignal.cryptotrading.services.impl;

import com.tradingsignal.cryptotrading.constants.Constants;
import com.tradingsignal.cryptotrading.dtos.PriceMessage;
import com.tradingsignal.cryptotrading.enums.Symbol;
import com.tradingsignal.cryptotrading.services.PriceAggregationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceAggregationServiceImpl implements PriceAggregationService {
    @Value("${binance.url}")
    private String binanceUrl;
    @Value("${huobi.url}")
    private String huobiUrl;
    @Value("${kafka.price.topic}")
    private String topic;

    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, PriceMessage> kafkaTemplate;

    /**
     * This method acts as the producer
     * to send the price into kafka topics
     */
    @Scheduled(fixedDelay = 10000)
    @Override
    public void aggregatePrices() {
        for (Symbol symbol : Symbol.values()) {
            /** Flow should be:
             * 1: fetch Binance price & Huobi
             * 2: compare 2 value of bid => take the highest one for sell
             * 3: compare 2 value of ask => take the lowest one for buy
             * 4: push to topic for consumer to save
             **/
            var binancePrice = fetchBinance(symbol);
            var huobiPrice = fetchHuobi(symbol);

            var highestBid = binancePrice[0].max(huobiPrice[0]);
            var lowestAsk = binancePrice[0].min(huobiPrice[0]);

            PriceMessage message = new PriceMessage();
            message.setSymbol(symbol);
            message.setBid(highestBid);
            message.setAsk(lowestAsk);

            // Send the price to topic for consumer to use
            kafkaTemplate.send(topic, message);
            log.info("Price aggregation sent to topic {}, with ask: {}, bid: {}", topic, lowestAsk, highestBid);
        }
    }

    private BigDecimal[] fetchBinance(Symbol symbol) {
        Map<String, String>[] tickers = restTemplate.getForObject(binanceUrl, Map[].class);

        if (tickers == null || tickers.length == 0) {
            return new BigDecimal[0];
        }

        for (Map<String, String> t : tickers) {
            if (symbol.name().equals(t.get(Constants.SYMBOL))) {
                return new BigDecimal[]{
                        new BigDecimal(t.get(Constants.BINANCE_BID_PRICE)),
                        new BigDecimal(t.get(Constants.BINANCE_ASK_PRICE))
                };
            }
        }
        return new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO};
    }

    private BigDecimal[] fetchHuobi(Symbol symbol) {
        Map<String, Object> response = restTemplate.getForObject(huobiUrl, Map.class);
        /* Response from Huobi return is as
         * data: [array of symbols]
         * => Need to create a List to store the data
         * and get the corresponding symbols
         */
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get(Constants.HUOBI_DATA);
        String sym = symbol.name().toLowerCase();

        if (CollectionUtils.isEmpty(data)) {
            return new BigDecimal[0];
        }

        for (Map<String, Object> t : data) {
            if (sym.equals(t.get(Constants.SYMBOL))) {
                return new BigDecimal[]{
                        new BigDecimal(t.get(Constants.HUOBI_BID_PRICE).toString()),
                        new BigDecimal(t.get(Constants.HUOBI_ASK_PRICE).toString())
                };
            }
        }
        return new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO};
    }
}
