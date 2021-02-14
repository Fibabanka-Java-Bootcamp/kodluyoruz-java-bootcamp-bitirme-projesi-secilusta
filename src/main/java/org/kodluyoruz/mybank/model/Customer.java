package org.kodluyoruz.mybank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.kodluyoruz.mybank.model.creditcard.CreditCard;
import org.kodluyoruz.mybank.model.debitcard.DebitCard;
import org.kodluyoruz.mybank.model.enumeration.CustomerType;
import org.kodluyoruz.mybank.model.enumeration.converter.CustomerTypeConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@ApiModel(description = "Class representing a customer.")
public class Customer extends BaseEntity {
    @NotNull
    @ApiModelProperty(notes = "Unique identifier of the customer. No two persons can have the same id.", example = "123", required = true, position = 0)
    private Long customerNo;
    @Column(length = 50)
    @ApiModelProperty(notes = "First name of the person.", example = "John", required = true, position = 1)
    private String name;
    @Column(length = 50)
    @ApiModelProperty(notes = "Last name of the person.", example = "Doe", required = true, position = 2)
    private String surname;
    private Long tckn;

    @Convert(converter = CustomerTypeConverter.class)
    private CustomerType customerType;

    private LocalDate birthdate;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    //@JsonManagedReference
    @JsonIgnore
    //@JsonIgnoreProperties(value = "customer",allowSetters = true)
    private Set<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    //@JsonManagedReference
    @JsonIgnore
    private Set<CreditCard> creditCards;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    //@JsonManagedReference
    @JsonIgnore
    private Set<DebitCard> debitCards;


}
