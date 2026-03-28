package com.tradingsignal.cryptotrading.services;

import com.tradingsignal.cryptotrading.entities.Trade;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    List<Trade> getUserTransactionsHistory(Long id);
}
