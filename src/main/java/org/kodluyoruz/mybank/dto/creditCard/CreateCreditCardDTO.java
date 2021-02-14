package org.kodluyoruz.mybank.dto.creditCard;

import lombok.Data;
import org.kodluyoruz.mybank.model.enumeration.CardStatus;

@Data
public class CreateCreditCardDTO {
    private Long customerNo;
    private String embossName;
    private String productCode;
    private String logoCode;
}