package org.kodluyoruz.mybank.service.account;

import org.kodluyoruz.mybank.dto.account.BankAccountDTO;
import org.kodluyoruz.mybank.dto.account.CreateBankAccountDTO;
import org.kodluyoruz.mybank.model.BankAccount;
import org.kodluyoruz.mybank.repository.AccountRepository;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.kodluyoruz.mybank.service.customer.CustomerService;
import org.kodluyoruz.mybank.util.Common;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankAccountService implements IBankAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final ModelMapper mapper;

    @Autowired
    public BankAccountService(AccountRepository accountRepository,
                              CustomerRepository customerRepository,
                              CustomerService customerService,
                              ModelMapper mapper) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @Override
    public BankAccountDTO get(long accountNo) {
        Common.throwIfAccountNotExists(accountNo);
        BankAccount acc = accountRepository.findByBankAccountNo(accountNo).get();
        return mapper.map(acc, BankAccountDTO.class);
    }

    @Override
    public BankAccountDTO create(@RequestBody CreateBankAccountDTO dto) {
        Common.throwIfCustomerNotExists(dto.getCustomerNo());
        Common.throwIfAccountExists(dto.getBankAccountNo());

        BankAccount acc = mapper.map(dto, BankAccount.class);
        acc.setCustomer(customerRepository.findByCustomerNo(dto.getCustomerNo()).get());
        accountRepository.save(acc);

        return mapper.map(acc, BankAccountDTO.class);

    }

    @Override
    public BankAccountDTO delete(long accountNo) {
        Common.throwIfAccountNotExists(accountNo);
        BankAccount acc = accountRepository.findByBankAccountNo(accountNo).get();
        accountRepository.deleteById(acc.getId());
        return mapper.map(acc, BankAccountDTO.class);
    }

    @Override
    public List<BankAccountDTO> getCustomerAccounts(long customerNo) {
        Common.throwIfCustomerNotExists(customerNo);
        return accountRepository.findAllByCustomer_CustomerNo(customerNo).get().stream()
                .map(source -> mapper.map(source, BankAccountDTO.class))
                .collect(Collectors.toList());
    }
}
