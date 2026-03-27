package com.tradingsignal.cryptotrading.entities;

import com.tradingsignal.cryptotrading.enums.Symbol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Symbol symbol;

    private BigDecimal bid;
    private BigDecimal ask;

    private LocalDateTime timestamp;
}
