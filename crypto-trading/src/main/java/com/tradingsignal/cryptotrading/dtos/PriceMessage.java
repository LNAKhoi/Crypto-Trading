package com.tradingsignal.cryptotrading.dtos;

import com.tradingsignal.cryptotrading.enums.Symbol;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PriceMessage {
    private Symbol symbol;
    private BigDecimal bid;
    private BigDecimal ask;
}
