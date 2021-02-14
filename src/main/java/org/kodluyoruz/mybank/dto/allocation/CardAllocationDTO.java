package org.kodluyoruz.mybank.dto.allocation;

import lombok.Data;

@Data
public class CardAllocationDTO {
    private String cardNo;
    private Integer expiryDate;
}
