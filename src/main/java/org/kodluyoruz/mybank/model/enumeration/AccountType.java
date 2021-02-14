package org.kodluyoruz.mybank.model.enumeration;

import lombok.Getter;
import org.kodluyoruz.mybank.model.enumeration.converter.AccountTypeConverter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum AccountType {
    CHECKING ("C", "Vadesiz Mevduat Hesabı"),
    SAVING ("S", "Birikim Hesabı");

    private final String code;
    private final String description;

    private static class Holder {
        static Map<String, AccountType> CODE_MAP = new HashMap<>();
    }

    AccountType(String code, String description) {
        this.code = code;
        this.description = description;
        Holder.CODE_MAP.put(code, this);
    }

    public static AccountType convertFromString(String code) {
        return Holder.CODE_MAP.get(code);
    }
}