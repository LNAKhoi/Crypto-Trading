package com.tradingsignal.cryptotrading.repositories;

import com.tradingsignal.cryptotrading.entities.Price;
import com.tradingsignal.cryptotrading.enums.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    Optional<Price> findPriceBySymbol(Symbol symbol);
}
