package com.tradingsignal.cryptotrading.controller;

import com.tradingsignal.cryptotrading.dtos.TradeInputDto;
import com.tradingsignal.cryptotrading.entities.Trade;
import com.tradingsignal.cryptotrading.services.TradeService;
import com.tradingsignal.cryptotrading.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TradeController {
    private final TradeService tradeService;
    private final TransactionService transactionService;

    // TASK 3: allows user to trade base on best price
    @PostMapping("/api/trade")
    public ResponseEntity<Trade> trade(@RequestBody TradeInputDto dto) {
        return ResponseEntity.ok(tradeService.excecuteTrade(dto));
    }

    // TASK 5: retrieve transaction history
    @GetMapping("/api/trades/{userId}")
    public ResponseEntity<List<Trade>> getTradeHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getUserTransactionsHistory(userId));
    }
}
