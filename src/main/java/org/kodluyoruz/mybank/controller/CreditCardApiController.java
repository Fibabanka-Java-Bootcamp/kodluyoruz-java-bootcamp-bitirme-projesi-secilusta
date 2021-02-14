package org.kodluyoruz.mybank.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kodluyoruz.mybank.dto.creditCard.CreateCreditCardDTO;
import org.kodluyoruz.mybank.dto.creditCard.CreditCardDTO;
import org.kodluyoruz.mybank.dto.customer.CreateCustomerDTO;
import org.kodluyoruz.mybank.dto.customer.CustomerDTO;
import org.kodluyoruz.mybank.dto.customer.UpdateCustomerDTO;
import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.model.creditcard.CreditCard;
import org.kodluyoruz.mybank.service.creditCard.ICreditCardService;
import org.kodluyoruz.mybank.service.customer.ICustomerService;
import org.kodluyoruz.mybank.util.Common;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/creditcard")
@Validated
@Api(tags = "Credit Card Api")
public class CreditCardApiController {

    private final ICreditCardService creditCardService;

    public CreditCardApiController(ICreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Creates new credit card")
    public CreditCardDTO create (@RequestBody CreateCreditCardDTO dto) {
        //Common.throwIfCustomerExists(dto.getCustomerNo());
        return creditCardService.create(dto);
    }
/*
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Updates given customer")
    public CustomerDTO update (@RequestBody UpdateCustomerDTO dto) {
        return creditCardService.update(dto);
    }*/

    @GetMapping(value = "/{cardNo}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get credit card with cardNo")
    public CreditCardDTO get(@PathVariable String cardNo) {
        return creditCardService.get(cardNo);
    }

    @GetMapping(value = "/customerCards/{customerNo}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get credit cards of customer")
    public List<CreditCardDTO> getCardsOfCustomer(@PathVariable long customerNo) {
        return creditCardService.getCardsOfCustomer(customerNo);
    }


    @DeleteMapping(value = "/{cardNo}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete card with cardNo")
    public CreditCardDTO delete(@PathVariable long cardNo) {
        return creditCardService.delete(String.valueOf(cardNo));
    }


}