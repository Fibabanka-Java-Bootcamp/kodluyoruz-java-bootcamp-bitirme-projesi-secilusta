package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.debitcard.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DebitCardRepository extends JpaRepository<DebitCard, Long> {
    Optional<DebitCard> findByCardNo(String cardNo);
    Optional<DebitCard> findByDebitCardAccount_AccountNo(long accountNo);
}
