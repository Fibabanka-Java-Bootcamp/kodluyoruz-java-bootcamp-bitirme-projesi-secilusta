package org.kodluyoruz.mybank.dto.account;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateBankAccountDTO {
    private Long id;
    private Long bankAccountNo;
    private int currencyCode;
    private String accountType;
    private String IBAN;
    private LocalDate openingDate;
}