package org.kodluyoruz.mybank.model.debitcard;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kodluyoruz.mybank.model.BankAccount;
import org.kodluyoruz.mybank.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@ApiModel(description = "Class representing an debit card account.")
//created for same reasons in credit card account
public class DebitCardAccount extends BaseEntity {
    @NotNull
    private Long accountNo;
    private int isCashDepositAllowed;
    private int isCashWithdrawalAllowed;
    private LocalDate openingDate;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "debitCardAccount")
    private Set<DebitCard> debitCardSet;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "debitCardAccount")
    private Set<BankAccount> bankAccountSet;

}
