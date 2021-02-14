package org.kodluyoruz.mybank.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kodluyoruz.mybank.dto.account.BankAccountDTO;
import org.kodluyoruz.mybank.dto.account.CreateBankAccountDTO;
import org.kodluyoruz.mybank.service.account.IBankAccountService;
import org.kodluyoruz.mybank.service.customer.ICustomerService;
import org.kodluyoruz.mybank.util.Common;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/account")
@Validated
@Api(tags = "Account Api")
public class AccountApiController {

    private final IBankAccountService accountService;
    private final ICustomerService customerService;

    public AccountApiController(IBankAccountService accountService, ICustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @PostMapping()
    @ApiOperation(value = "Creates new account")
    public BankAccountDTO createAccount (@Valid @RequestBody CreateBankAccountDTO dto) {
        return accountService.create(dto);
    }

    @GetMapping("/{accountNo}")
    @ApiOperation(value = "Gets account info")
    public BankAccountDTO get (@PathVariable long accountNo) {
        if (accountService.get(accountNo) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such account");
        }
        return accountService.get(accountNo);
    }

    @GetMapping("/customerAccounts/{customerNo}")
    @ApiOperation(value = "Get accounts info of customer")
    public List<BankAccountDTO> getCustomerAccounts (@PathVariable long customerNo) {
        Common.throwIfCustomerNotExists(customerNo);
        return accountService.getCustomerAccounts(customerNo);
    }

    @DeleteMapping("/{accountNo}")
    @ApiOperation(value = "Deletes an account")
    public BankAccountDTO deleteAccount (@PathVariable long accountNo) {
        return accountService.delete(accountNo);
    }

}
