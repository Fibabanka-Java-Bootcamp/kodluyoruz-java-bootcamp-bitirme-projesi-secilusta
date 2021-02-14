package org.kodluyoruz.mybank.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StmtStatus {
    NORMAL("N", "Normal"),
    GECIKME("O","Gecikme");
   // DONEM_GECIKME("D","Dönem Gecikme")

    private String code;
    private String description;
}
