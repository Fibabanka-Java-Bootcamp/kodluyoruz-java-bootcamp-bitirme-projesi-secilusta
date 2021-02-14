package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    Optional<CreditCard> findByCardNo(String cardNo);
    Optional<CreditCard> findByCreditCardAccount_AccountNo(long accountNo);
    Optional<List<CreditCard>> findAllByCustomer_CustomerNo(long customerNo);
    boolean existsByCardNo(String cardNo);
}
