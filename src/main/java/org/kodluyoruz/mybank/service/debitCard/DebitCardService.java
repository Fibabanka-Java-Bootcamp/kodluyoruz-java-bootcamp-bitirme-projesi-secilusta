package org.kodluyoruz.mybank.service.debitCard;

import org.kodluyoruz.mybank.dto.debitCard.DebitCardDTO;
import org.kodluyoruz.mybank.model.debitcard.DebitCard;
import org.kodluyoruz.mybank.repository.DebitCardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
public class DebitCardService implements IDebitCardService {

    private final DebitCardRepository debitCardRepository;
    private final ModelMapper mapper;

    @Autowired
    public DebitCardService(DebitCardRepository debitCardRepository, ModelMapper mapper) {
        this.debitCardRepository = debitCardRepository;
        this.mapper = mapper;
    }

    @Override
    public DebitCardDTO get(String cardNo) {
        if (debitCardRepository.findByCardNo(cardNo).isPresent()) {
            DebitCard crd = debitCardRepository.findByCardNo(cardNo).get();
            return mapper.map(crd, DebitCardDTO.class);
        }
        return null;
    }

    @Override
    public DebitCardDTO createCreditCard(@RequestBody DebitCardDTO dto) {
        DebitCard crd = mapper.map(dto,DebitCard.class);
        debitCardRepository.save(crd);
        return mapper.map(crd, DebitCardDTO.class);
    }
}
