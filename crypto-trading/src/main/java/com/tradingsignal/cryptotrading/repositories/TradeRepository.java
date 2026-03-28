package com.tradingsignal.cryptotrading.repositories;

import com.tradingsignal.cryptotrading.entities.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
