package org.kodluyoruz.mybank.service.account;

import org.kodluyoruz.mybank.dto.account.BankAccountDTO;
import org.kodluyoruz.mybank.dto.account.CreateBankAccountDTO;

import java.util.List;

public interface IBankAccountService {
    BankAccountDTO create(CreateBankAccountDTO dto);
   // AccountDTO updateAccount(UpdateAccountDTO dto);
    BankAccountDTO get(long accountNo);
    BankAccountDTO delete(long accountNo);
    List<BankAccountDTO> getCustomerAccounts(long customerNo);
}
