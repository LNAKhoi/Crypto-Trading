package com.tradingsignal.cryptotrading.dtos;

import com.tradingsignal.cryptotrading.enums.Symbol;
import com.tradingsignal.cryptotrading.enums.TradeSide;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class TradeInputDto {
    Long userId;
    Symbol symbol;
    TradeSide side;
    BigDecimal quantity;
}
