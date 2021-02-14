package org.kodluyoruz.mybank.model.enumeration.converter;

import org.kodluyoruz.mybank.model.enumeration.CustomerType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CustomerTypeConverter implements AttributeConverter<CustomerType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CustomerType customerType) {
        return (customerType != null) ? customerType.getCode() : null;
    }

    @Override
    public CustomerType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return Stream.of(CustomerType.values())
                .filter(c -> c.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}