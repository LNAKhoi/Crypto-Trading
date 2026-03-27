package com.tradingsignal.cryptotrading.entities;

import com.tradingsignal.cryptotrading.enums.Symbol;
import com.tradingsignal.cryptotrading.enums.TradeSide;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "trades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Symbol symbol;
    private TradeSide side;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal total;
    private LocalDateTime timestamp;
}
