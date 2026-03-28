package com.tradingsignal.cryptotrading.services;

import com.tradingsignal.cryptotrading.entities.Trade;

import java.util.List;

public interface TransactionService {
    List<Trade> getUserTransactionsHistory(Long id);
}
