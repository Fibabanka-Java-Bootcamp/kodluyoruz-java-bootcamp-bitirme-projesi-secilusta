package org.kodluyoruz.mybank.model.enumeration;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum CurrencyType {
    TL (949, "Türk Lirası"),
    EURO (978, "Euro"),
    DOLLAR(840, "Dolar");

    private final int code;
    private final String description;

    private static class Holder {
        static Map<Integer, CurrencyType> CODE_MAP = new HashMap<>();
    }

    CurrencyType(Integer code, String description) {
        this.code = code;
        this.description = description;
        CurrencyType.Holder.CODE_MAP.put(code, this);
    }

    public static CurrencyType convertFromInt(int code) {
        return CurrencyType.Holder.CODE_MAP.get(code);
    }
}
