package org.kodluyoruz.mybank.model.enumeration.converter;

import org.kodluyoruz.mybank.model.enumeration.CurrencyType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CurrencyCodeConverter implements AttributeConverter<CurrencyType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CurrencyType currencyCode) {
        return (currencyCode != null) ? currencyCode.getCode(): null;
    }

    @Override
    public CurrencyType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return Stream.of(CurrencyType.values())
                .filter(c -> c.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}