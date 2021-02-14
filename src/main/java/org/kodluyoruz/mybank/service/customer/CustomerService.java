package org.kodluyoruz.mybank.service.customer;

import org.kodluyoruz.mybank.dto.customer.CreateCustomerDTO;
import org.kodluyoruz.mybank.dto.customer.CustomerDTO;
import org.kodluyoruz.mybank.dto.customer.UpdateCustomerDTO;
import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    public List<CustomerDTO> findAll() {
       return customerRepository.findAll()
                .stream()
                .map(source -> mapper.map(source, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    public CustomerDTO get(long customerNo) {
        if (customerRepository.findByCustomerNo(customerNo).isPresent()){
            Customer cst = customerRepository.findByCustomerNo(customerNo).get();
            return mapper.map(cst, CustomerDTO.class);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No customer found with customer no:" + customerNo);
    }

    public Customer getCustomer(long customerNo) {
        if (customerRepository.findByCustomerNo(customerNo).isPresent()){
            return customerRepository.findByCustomerNo(customerNo).get();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No customer found with customer no:" + customerNo);
    }

    public CustomerDTO create(@RequestBody CreateCustomerDTO dto) {
        Customer cst = mapper.map(dto, Customer.class);
        cst.setBankAccounts(Collections.emptySet());
        cst.setCreditCards(Collections.emptySet());
        cst.setDebitCards(Collections.emptySet());
        customerRepository.save(cst);
        return mapper.map(cst, CustomerDTO.class);
    }

    public CustomerDTO update(@RequestBody UpdateCustomerDTO dto) {
        if (customerRepository.findByCustomerNo(dto.getCustomerNo()).isPresent()) {
            Customer cst = customerRepository.findByCustomerNo(dto.getCustomerNo()).get();
            cst.setName(dto.getName());
            cst.setSurname(dto.getSurname());
            cst.setTckn(dto.getTckn());
            cst.setCustomerType(dto.getCustomerType());
            cst.setBirthdate(dto.getBirthdate());
            customerRepository.save(cst);
            return mapper.map(cst, CustomerDTO.class);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No customer found with customer no:" + dto.getCustomerNo());
    }

    public CustomerDTO delete(long customerNo) {
        if (customerRepository.findByCustomerNo(customerNo).isPresent()){
            Customer cst = customerRepository.findByCustomerNo(customerNo).get();
            customerRepository.delete(cst);
            return mapper.map(cst, CustomerDTO.class);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No customer found with customer no:" + customerNo);
    }
}
