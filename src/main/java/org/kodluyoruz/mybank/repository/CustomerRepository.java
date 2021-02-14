package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.BankAccount;
import org.kodluyoruz.mybank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.customerNo = ?1")
    Optional<Customer> findByCustomerNo(long customerNo);
    boolean existsByCustomerNo(long customerNo);
}
