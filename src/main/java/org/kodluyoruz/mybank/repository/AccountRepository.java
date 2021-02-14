package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.BankAccount;
import org.kodluyoruz.mybank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByBankAccountNo(long accountNo);
    Optional<List<BankAccount>> findAllByCustomer_CustomerNo(long customerNo);
    boolean existsByBankAccountNo(long accountNo);

}
