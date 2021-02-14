package org.kodluyoruz.mybank.dto.account;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BankAccountDTO {
    private Long bankAccountNo;
    private int currencyCode;
    private String currencyDescription;
    private String accountType;
    private String IBAN;
    private LocalDate openingDate;
    //private Customer customer;
    private Long customerNo;
    private Integer branchCode;
    private Long debitCardAccount;
}