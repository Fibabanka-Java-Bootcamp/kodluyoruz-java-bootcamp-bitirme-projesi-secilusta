package org.kodluyoruz.mybank.service.customer;

import org.kodluyoruz.mybank.dto.customer.CreateCustomerDTO;
import org.kodluyoruz.mybank.dto.customer.CustomerDTO;
import org.kodluyoruz.mybank.dto.customer.UpdateCustomerDTO;
import org.kodluyoruz.mybank.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> findAll();
    CustomerDTO create(CreateCustomerDTO dto);
    CustomerDTO update(UpdateCustomerDTO dto);
    CustomerDTO get(long id);
    CustomerDTO delete(long id);
    }
