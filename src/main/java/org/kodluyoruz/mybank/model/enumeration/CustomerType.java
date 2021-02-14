package org.kodluyoruz.mybank.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.model.enumeration.converter.CustomerTypeConverter;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum CustomerType {
    STUDENT (1, "Öğrenci"),
    MEMBER_INDIVIDUAL(2, "Bireysel Mensup"),
    SUBSIDIARY_MEMBER(3, "İştirak Çalışanı"),
    CUSTOMER(4, "Müşteri"),
    CORPORATE_CUSTOMER(5, "Ticari");

    @Id
    private final int code;
    private final String description;
/*
    private static class Holder {
        static Map<Integer, CustomerType> CODE_MAP = new HashMap<>();
    }

    CustomerType(Integer code, String description) {
        this.code = code;
        this.description = description;
        CustomerType.Holder.CODE_MAP.put(code, this);
    }

    public static CustomerType convertFromInt(int code) {
        return CustomerType.Holder.CODE_MAP.get(code);
    }
 */
}
