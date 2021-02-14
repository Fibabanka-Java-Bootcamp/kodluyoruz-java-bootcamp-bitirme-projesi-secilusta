package org.kodluyoruz.mybank.model.creditcard;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.kodluyoruz.mybank.model.BaseEntity;
import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.model.enumeration.CardStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@ApiModel(description = "Class representing an credit card.")
public class CreditCard extends BaseEntity {

    @NotNull
    @NotBlank
    private String cardNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerNo", referencedColumnName = "customerNo")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AccountNo", referencedColumnName = "accountNo")
    private CreditCardAccount creditCardAccount;

    @Column(length = 150)
    private String embossName;

    private Integer expiryDate;
    private CardStatus cardStatus;
    private String productCode;
    private String logoCode;

}
