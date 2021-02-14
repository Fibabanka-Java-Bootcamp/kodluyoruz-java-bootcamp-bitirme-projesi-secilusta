package org.kodluyoruz.mybank.service.allocation;

import org.kodluyoruz.mybank.dto.allocation.CardAllocationDTO;
import org.kodluyoruz.mybank.dto.creditCard.CreateCreditCardDTO;

public interface ICardAllocationService {
    CardAllocationDTO allocateCard(CreateCreditCardDTO dto);
}
