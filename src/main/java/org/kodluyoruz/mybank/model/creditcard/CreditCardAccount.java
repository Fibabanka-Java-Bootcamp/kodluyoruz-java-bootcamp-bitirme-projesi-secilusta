package org.kodluyoruz.mybank.model.creditcard;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.kodluyoruz.mybank.model.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@ApiModel(description = "Class representing an credit card account.")
//a credit card could get lost for example, then we will create another card connected to the same account but different card no,
//and statement info couldn't get lost in this process, so we'll keep them in card account.
public class CreditCardAccount extends BaseEntity {
    @NotNull
    private Long accountNo;
    /*private StmtStatus stmtStatus;
    private LocalDate lastStmtDate;
    private LocalDate lastDueDate;
    private LocalDate nextStmtDate;
    private LocalDate nextDueDate;
    //example fields
    */
    private LocalDate openingDate;
    private String lastCardNo;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "creditCardAccount")
    private Set<CreditCard> creditCardSet;

}
