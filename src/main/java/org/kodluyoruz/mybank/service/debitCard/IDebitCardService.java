package org.kodluyoruz.mybank.service.debitCard;

import org.kodluyoruz.mybank.dto.debitCard.DebitCardDTO;

public interface IDebitCardService {
    DebitCardDTO createCreditCard(DebitCardDTO dto);
    DebitCardDTO get(String cardNo);
}
