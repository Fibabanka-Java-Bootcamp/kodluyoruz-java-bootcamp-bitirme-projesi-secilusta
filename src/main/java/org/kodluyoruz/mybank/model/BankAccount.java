package org.kodluyoruz.mybank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.kodluyoruz.mybank.model.debitcard.DebitCardAccount;
import org.kodluyoruz.mybank.model.enumeration.AccountType;
import org.kodluyoruz.mybank.model.enumeration.CurrencyType;
import org.kodluyoruz.mybank.model.enumeration.converter.AccountTypeConverter;
import org.kodluyoruz.mybank.model.enumeration.converter.CurrencyCodeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@ApiModel(description = "Class representing an account.")
public class BankAccount extends BaseEntity {
    @NotNull
    private Long bankAccountNo;

    @Convert(converter = CurrencyCodeConverter.class)
    private CurrencyType currencyCode;

    @Convert(converter = AccountTypeConverter.class)
    private AccountType accountType;

    @NotBlank
    @NotNull
    @Column(length = 26)
    //@Size(min = 26, max = 26)
    private String IBAN;

    private LocalDate openingDate;

    private Integer branchCode;

    @ManyToOne
    @JoinColumn(name = "customerNo", referencedColumnName = "customerNo")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "accountNo", referencedColumnName = "accountNo")
    @JsonBackReference
    private DebitCardAccount debitCardAccount;

}
