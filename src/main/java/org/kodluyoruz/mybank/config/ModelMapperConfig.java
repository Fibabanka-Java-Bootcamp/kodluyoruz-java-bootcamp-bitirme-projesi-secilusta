package org.kodluyoruz.mybank.config;

import org.kodluyoruz.mybank.dto.account.BankAccountDTO;
import org.kodluyoruz.mybank.dto.account.CreateBankAccountDTO;
import org.kodluyoruz.mybank.dto.creditCard.CreditCardDTO;
import org.kodluyoruz.mybank.model.BankAccount;
import org.kodluyoruz.mybank.model.Customer;
import org.kodluyoruz.mybank.model.creditcard.CreditCard;
import org.kodluyoruz.mybank.model.creditcard.CreditCardAccount;
import org.kodluyoruz.mybank.model.debitcard.DebitCardAccount;
import org.kodluyoruz.mybank.model.enumeration.AccountType;
import org.kodluyoruz.mybank.model.enumeration.CurrencyType;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.stream.Stream;

@Configuration
public class ModelMapperConfig {

    PropertyMap<BankAccount, BankAccountDTO> BankAccount_BankAccountDTO_mapper;

    @Bean
    @Scope("singleton")
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
        addConvertersForBankAccount(modelMapper);
        addConvertersForCreditCard(modelMapper);
        return modelMapper;
    }

    private void addConvertersForBankAccount(ModelMapper modelMapper) {

        Converter<String, AccountType> convertStringToAccountType = (context) ->
                Stream.of(AccountType.values())
                        .filter(c -> c.getCode().equals(context.getSource()))
                        .findFirst()
                        .orElseThrow(IllegalArgumentException::new);

        Converter<Integer, CurrencyType> convertIntToCurrencyCode = (context) ->
                Stream.of(CurrencyType.values())
                        .filter(c -> c.getCode() == context.getSource())
                        .findFirst()
                        .orElseThrow(IllegalArgumentException::new);

        PropertyMap<CreateBankAccountDTO, BankAccount> mymap = new PropertyMap<>() {
            protected void configure() {
                using(convertStringToAccountType).map(source.getAccountType()).setAccountType(null);
                using(convertIntToCurrencyCode).map(source.getCurrencyCode()).setCurrencyCode(null);
            }
        };

        Converter<CurrencyType, String> getCurrencyCode_Desc = (context) -> context.getSource().getDescription();
        Converter<CurrencyType, Integer> getCurrencyCode_Code = (context) -> context.getSource().getCode();
        Converter<AccountType, String> getAccountType_Desc = (context) -> context.getSource().getDescription();
        Converter<Customer, Long> getCustomerNo = (context) -> context.getSource().getCustomerNo();
        Converter<DebitCardAccount, Long> getDebitAccountNo = (context) -> context.getSource().getAccountNo();

        BankAccount_BankAccountDTO_mapper = new PropertyMap<>() {
            protected void configure() {
                using(getCurrencyCode_Code).map(source.getCurrencyCode()).setCurrencyCode(0);
                using(getCurrencyCode_Desc).map(source.getCurrencyCode()).setCurrencyDescription("");
                using(getAccountType_Desc).map(source.getAccountType()).setAccountType("");
                using(getCustomerNo).map(source.getCustomer()).setCustomerNo(0L);
                using(getDebitAccountNo).map(source.getDebitCardAccount()).setDebitCardAccount(0L);
            }
        };

        modelMapper.addMappings(mymap);
        modelMapper.addMappings(BankAccount_BankAccountDTO_mapper);
    }

    private void addConvertersForCreditCard(ModelMapper modelMapper) {

        Converter<Customer, Long> getCustomerNo = (context) -> context.getSource().getCustomerNo();
        Converter<CreditCardAccount,Long> getCreditCardAccountNo = (context) -> context.getSource().getAccountNo();

        PropertyMap<CreditCard, CreditCardDTO> mymap = new PropertyMap<>() {
            protected void configure() {
                using(getCustomerNo).map(source.getCustomer()).setCustomerNo(0L);
                using(getCreditCardAccountNo).map(source.getCreditCardAccount()).setCreditCardAccountNo(0L);
            }
        };

        modelMapper.addMappings(mymap);
    }

}
