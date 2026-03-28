package com.tradingsignal.cryptotrading.repositories;

import com.tradingsignal.cryptotrading.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findWalletByUserIdAndCurrency(Long userId, String currency);
}
