package org.kodluyoruz.mybank.model.debitcard;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kodluyoruz.mybank.model.BaseEntity;
import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.model.enumeration.CardStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@ApiModel(description = "Class representing an debit card.")
public class DebitCard extends BaseEntity {
    @NotNull
    @NotBlank
    private String cardNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerNo", referencedColumnName = "customerNo", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accountNo", referencedColumnName = "accountNo", nullable = false)
    private DebitCardAccount debitCardAccount;

    @Column(length = 150)
    private String embossName;

    private LocalDate expiryDate;
    private CardStatus cardStatus;
    private String productCode;
    private String logoCode;
}
