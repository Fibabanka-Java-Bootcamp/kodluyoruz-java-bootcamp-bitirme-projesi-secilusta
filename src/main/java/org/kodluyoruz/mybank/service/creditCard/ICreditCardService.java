package org.kodluyoruz.mybank.service.creditCard;

import org.kodluyoruz.mybank.dto.creditCard.CreateCreditCardDTO;
import org.kodluyoruz.mybank.dto.creditCard.CreditCardDTO;
import org.kodluyoruz.mybank.dto.customer.CustomerDTO;

import java.util.List;

public interface ICreditCardService {
    CreditCardDTO create(CreateCreditCardDTO dto);
    CreditCardDTO get(String cardNo);
    CreditCardDTO delete(String cardNo);
    List<CreditCardDTO> getCardsOfCustomer(long customerNo);
}
