package com.tradingsignal.cryptotrading.controller;

import com.tradingsignal.cryptotrading.entities.Price;
import com.tradingsignal.cryptotrading.enums.Symbol;
import com.tradingsignal.cryptotrading.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PriceController {
    private final PriceRepository priceRepository;

    /** TASK 2: get latest best aggregation price
     *  because in PriceConsumer we will always findOrCreate
     *  so there the price will always be updated with best
     *  price.
     */
    @GetMapping("/api/prices-latest")
    public ResponseEntity<List<Price>> getLatestPrices() {
        return ResponseEntity.ok(priceRepository.findAll());
    }

    // Additional, we could get by symbol.
    @GetMapping("/api/prices-latest/{symbol}")
    public ResponseEntity<Price> getPrice(@PathVariable Symbol symbol) {
        return priceRepository.findPriceBySymbol(symbol)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
