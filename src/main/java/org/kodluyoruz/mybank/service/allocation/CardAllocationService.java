package org.kodluyoruz.mybank.service.allocation;

import org.kodluyoruz.mybank.dto.allocation.CardAllocationDTO;
import org.kodluyoruz.mybank.dto.creditCard.CreateCreditCardDTO;
import org.kodluyoruz.mybank.dto.creditCard.CreditCardDTO;
import org.kodluyoruz.mybank.model.creditcard.CreditCard;
import org.kodluyoruz.mybank.model.enumeration.CardType;
import org.kodluyoruz.mybank.repository.CreditCardRepository;
import org.kodluyoruz.mybank.service.creditCard.ICreditCardService;
import org.kodluyoruz.mybank.service.customer.CustomerService;
import org.kodluyoruz.mybank.util.CardNumberGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class CardAllocationService implements ICardAllocationService {

    @Override
    public CardAllocationDTO allocateCard(CreateCreditCardDTO dto) {
        //passing dto just for future cases.
        CardAllocationDTO cardAllocationDTO = new CardAllocationDTO();
        cardAllocationDTO.setCardNo(CardNumberGenerator.generate(CardType.CREDIT));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");
        cardAllocationDTO.setExpiryDate(Integer.parseInt(LocalDate.now().plusYears(3).format(dateTimeFormatter)));
        return cardAllocationDTO;
    }
}
