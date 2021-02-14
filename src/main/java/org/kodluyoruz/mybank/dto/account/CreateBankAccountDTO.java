package org.kodluyoruz.mybank.dto.account;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class CreateBankAccountDTO {
    private Long bankAccountNo;
    private int currencyCode;
    private String accountType;
    private String IBAN;
    @NotNull
    private LocalDate openingDate;
    private Long customerNo;
    @NotNull
    private Integer branchCode;
}