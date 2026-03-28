package com.tradingsignal.cryptotrading.repositories;

import com.tradingsignal.cryptotrading.entities.Price;
import com.tradingsignal.cryptotrading.enums.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    Optional<Price> findPriceBySymbol(Symbol symbol);
}
