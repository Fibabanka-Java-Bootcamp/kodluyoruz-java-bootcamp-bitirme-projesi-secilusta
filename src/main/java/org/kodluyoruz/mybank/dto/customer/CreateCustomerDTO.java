package org.kodluyoruz.mybank.dto.customer;

import lombok.Data;
import org.kodluyoruz.mybank.model.enumeration.CustomerType;

import java.time.LocalDate;

@Data
public class CreateCustomerDTO {
    private Long customerNo;
    private String name;
    private String surname;
    private Long tckn;
    private CustomerType customerType;
    private LocalDate birthdate;
}
