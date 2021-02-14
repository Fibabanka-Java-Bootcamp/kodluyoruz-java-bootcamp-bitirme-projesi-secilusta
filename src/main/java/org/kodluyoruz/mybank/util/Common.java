package org.kodluyoruz.mybank.util;

import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.repository.AccountRepository;
import org.kodluyoruz.mybank.repository.CreditCardRepository;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class Common {

    private static CustomerRepository customerRepository;
    private static AccountRepository accountRepository;
    private static CreditCardRepository creditCardRepository;

    @Autowired
    private Common(CustomerRepository customerRepository,
                   AccountRepository accountRepository,
                   CreditCardRepository creditCardRepository) {
        Common.customerRepository = customerRepository;
        Common.accountRepository = accountRepository;
        Common.creditCardRepository = creditCardRepository;
    }

    private static boolean doesCustomerExist(long customerNo) {
        return customerRepository.existsByCustomerNo(customerNo);
    }

    public static void throwIfCustomerNotExists(long customerNo) {
        if (!doesCustomerExist(customerNo) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such customer found.");
    }

    public static void throwIfCustomerExists(long customerNo) {
        if (doesCustomerExist(customerNo) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This customer no is already in use.");
    }

    private static boolean doesAccountExist(long accountNo) {
        return accountRepository.existsByBankAccountNo(accountNo);
    }

    public static void throwIfAccountNotExists(long accountNo) {
        if (!doesAccountExist(accountNo) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such account found.");
    }

    public static void throwIfAccountExists(long accountNo) {
        if (doesAccountExist(accountNo) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This account no is already in use.");
    }

    public static void throwIfCardNoNotExists(String cardNo) {
        if (!creditCardRepository.existsByCardNo(cardNo))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such card no found.");
    }

}
