package com.tradingsignal.cryptotrading.controller;

import com.tradingsignal.cryptotrading.entities.Wallet;
import com.tradingsignal.cryptotrading.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserInfoController {
    private final WalletRepository walletRepository;

    // TASK 4: retrieve wallet
    @GetMapping("/api/wallet/{userId}")
    public ResponseEntity<List<Wallet>> getWallet(@PathVariable Long userId) {
        return ResponseEntity.ok(walletRepository.findByUserId(userId));
    }
}
