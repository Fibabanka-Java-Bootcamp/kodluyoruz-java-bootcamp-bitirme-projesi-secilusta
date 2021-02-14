package org.kodluyoruz.mybank.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kodluyoruz.mybank.dto.customer.CreateCustomerDTO;
import org.kodluyoruz.mybank.dto.customer.CustomerDTO;
import org.kodluyoruz.mybank.dto.customer.UpdateCustomerDTO;
import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.service.customer.ICustomerService;
import org.kodluyoruz.mybank.util.Common;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
@Validated
@Api(tags = "Customer Api")
public class CustomerApiController {

    private final ICustomerService customerService;

    public CustomerApiController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Creates new customer")
    public CustomerDTO create(@RequestBody CreateCustomerDTO dto) {
        Common.throwIfCustomerExists(dto.getCustomerNo());
        return customerService.create(dto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Updates given customer")
    public CustomerDTO update(@RequestBody UpdateCustomerDTO dto) {
        return customerService.update(dto);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "List all customers")
    public List<CustomerDTO> getAll() {
        return customerService.findAll();
    }

    @GetMapping(value = "/{customerNo}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get customer with customerNo")
    public CustomerDTO get(@PathVariable long customerNo) {
        return customerService.get(customerNo);
    }

    @DeleteMapping(value = "/{customerNo}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete customer with customerNo")
    public CustomerDTO delete(@PathVariable long customerNo) {
        return customerService.delete(customerNo);
    }
}