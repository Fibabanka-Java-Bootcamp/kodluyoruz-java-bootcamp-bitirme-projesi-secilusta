package org.kodluyoruz.mybank.service.creditCard;

import org.kodluyoruz.mybank.dto.allocation.CardAllocationDTO;
import org.kodluyoruz.mybank.dto.creditCard.CreateCreditCardDTO;
import org.kodluyoruz.mybank.dto.creditCard.CreditCardDTO;
import org.kodluyoruz.mybank.model.creditcard.CreditCard;
import org.kodluyoruz.mybank.model.creditcard.CreditCardAccount;
import org.kodluyoruz.mybank.model.enumeration.CardStatus;
import org.kodluyoruz.mybank.repository.CreditCardAccountRepository;
import org.kodluyoruz.mybank.repository.CreditCardRepository;
import org.kodluyoruz.mybank.service.allocation.CardAllocationService;
import org.kodluyoruz.mybank.service.customer.CustomerService;
import org.kodluyoruz.mybank.util.AccountNumberGenerator;
import org.kodluyoruz.mybank.util.Common;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditCardService implements ICreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final CreditCardAccountRepository creditCardAccountRepository;
    private final CustomerService customerService;
    private final CardAllocationService cardAllocationService;
    private final ModelMapper mapper;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository,
                             CreditCardAccountRepository creditCardAccountRepository,
                             CustomerService customerService,
                             CardAllocationService cardAllocationService,
                             ModelMapper mapper) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardAccountRepository = creditCardAccountRepository;
        this.customerService = customerService;
        this.cardAllocationService = cardAllocationService;
        this.mapper = mapper;
    }

    @Override
    public CreditCardDTO create(@RequestBody CreateCreditCardDTO dto) {
        CardAllocationDTO cardAllocationDTO = cardAllocationService.allocateCard(dto);
        CreditCard crd = mapper.map(dto, CreditCard.class);

        crd.setCustomer(customerService.getCustomer(dto.getCustomerNo()));
        crd.setCardNo(cardAllocationDTO.getCardNo());
        crd.setExpiryDate(cardAllocationDTO.getExpiryDate());
        crd.setCardStatus(CardStatus.NORMAL);

        Long accountNo = AccountNumberGenerator.generate();
        while (creditCardAccountRepository.findByAccountNo(accountNo).isPresent()) {
            accountNo = AccountNumberGenerator.generate();
        }
        CreditCardAccount creditCardAccount = new CreditCardAccount();
        creditCardAccount.setAccountNo(accountNo);
        creditCardAccount.setLastCardNo(crd.getCardNo());
        creditCardAccount.setOpeningDate(LocalDate.now());
        creditCardAccountRepository.save(creditCardAccount);
        crd.setCreditCardAccount(creditCardAccountRepository.findByAccountNo(accountNo).get());
        creditCardRepository.save(crd);
        return mapper.map(crd, CreditCardDTO.class);
    }

    @Override
    public CreditCardDTO get(String cardNo) {
        if (creditCardRepository.findByCardNo(cardNo).isPresent()) {
            CreditCard crd = creditCardRepository.findByCardNo(cardNo).get();
            return mapper.map(crd, CreditCardDTO.class);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No card found with card no:" + cardNo);
    }

    @Override
    public CreditCardDTO delete(String cardNo) {
        Common.throwIfCardNoNotExists(cardNo);
        CreditCard crd = creditCardRepository.findByCardNo(cardNo).get();
        creditCardRepository.deleteById(crd.getId());
        return mapper.map(crd,CreditCardDTO.class);
    }

    @Override
    public List<CreditCardDTO> getCardsOfCustomer(long customerNo) {
        if (creditCardRepository.findAllByCustomer_CustomerNo(customerNo).isPresent()) {
            List<CreditCard> crd = creditCardRepository.findAllByCustomer_CustomerNo(customerNo).get();
            return crd.stream()
                    .map(source -> mapper.map(source, CreditCardDTO.class))
                    .collect(Collectors.toList());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No card found under customer: " + customerNo);

    }
}
