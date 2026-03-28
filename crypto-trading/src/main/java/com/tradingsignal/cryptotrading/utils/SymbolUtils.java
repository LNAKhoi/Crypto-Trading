package com.tradingsignal.cryptotrading.utils;

import com.tradingsignal.cryptotrading.enums.Currency;
import com.tradingsignal.cryptotrading.enums.Symbol;
import com.tradingsignal.cryptotrading.enums.TradeSide;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class SymbolUtils {
    public static String getPriceSymbol(Symbol symbol) {
        if (symbol == null) {
            return StringUtils.EMPTY;
        }
        // get btc or eth only
        return symbol.name().toLowerCase().replaceAll(Currency.USDT.name().toLowerCase(), "");
    }

    public static TradeSide getSide(TradeSide side) {
        if (side == null) {
            return null;
        }
        return side == TradeSide.BUY ? TradeSide.BUY : TradeSide.SELL;
    }
}
