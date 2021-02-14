package org.kodluyoruz.mybank.dto.debitCard;

import lombok.Data;
import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.model.enumeration.CardStatus;

import java.time.LocalDate;

@Data
public class DebitCardDTO {
    private String cardNo;
    private Long customerNo;
    private Long debitCardAccountNo;
    private String embossName;
    private Integer expiryDate;
    private CardStatus cardStatus;
    private String productCode;
    private String logoCode;
}