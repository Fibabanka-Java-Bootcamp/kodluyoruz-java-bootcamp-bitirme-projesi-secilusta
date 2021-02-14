package org.kodluyoruz.mybank.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardType {
    CREDIT ("C", "Kredi Kartı"),
    DEBIT ("D", "Debit Kart"),
    PREPAID ("P", "Ön Ödemeli Kart");
    private String code;
    private String description;
}
