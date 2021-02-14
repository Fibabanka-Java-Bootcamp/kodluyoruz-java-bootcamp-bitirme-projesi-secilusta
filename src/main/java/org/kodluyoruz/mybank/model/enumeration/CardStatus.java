package org.kodluyoruz.mybank.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardStatus {
    NORMAL("N", "Normal"),
    Lost("L", "Kayıp"),
    Stolen("S", "Çalıntı"),
    Cancel("CM", "İptal"),
    Fake("FF", "Sahte");

    private final String code;
    private final String description;
}
