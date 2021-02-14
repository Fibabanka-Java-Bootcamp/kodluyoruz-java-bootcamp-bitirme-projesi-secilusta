package org.kodluyoruz.mybank.dto.creditCard;

import lombok.Data;
import org.kodluyoruz.mybank.model.enumeration.CardStatus;

import java.time.LocalDate;

@Data
public class CreditCardDTO {
    private String cardNo;
    private Long customerNo;
    private Long creditCardAccountNo;
    private String embossName;
    private Integer expiryDate;
    private CardStatus cardStatus;
    private String productCode;
    private String logoCode;
}