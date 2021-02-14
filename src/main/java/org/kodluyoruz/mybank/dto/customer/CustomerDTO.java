package org.kodluyoruz.mybank.dto.customer;

import lombok.Data;
import org.kodluyoruz.mybank.dto.account.BankAccountDTO;
import org.kodluyoruz.mybank.dto.creditCard.CreditCardDTO;
import org.kodluyoruz.mybank.dto.debitCard.DebitCardDTO;
import org.kodluyoruz.mybank.model.enumeration.CustomerType;

import java.time.LocalDate;
import java.util.Set;

@Data
public class CustomerDTO {
    private Long customerNo;
    private String name;
    private String surname;
    private Long tckn;
    private CustomerType customerType;
    private LocalDate birthdate;
}
