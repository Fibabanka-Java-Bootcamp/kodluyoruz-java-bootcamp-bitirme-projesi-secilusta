package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.creditcard.CreditCard;
import org.kodluyoruz.mybank.model.creditcard.CreditCardAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardAccountRepository extends JpaRepository<CreditCardAccount, Long> {
    Optional<CreditCardAccount> findByAccountNo(Long accountNo);
}
