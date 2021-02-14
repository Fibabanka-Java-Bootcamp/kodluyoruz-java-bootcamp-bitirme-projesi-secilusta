package org.kodluyoruz.mybank.util;

import org.kodluyoruz.mybank.model.enumeration.CardType;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;


public class CardNumberGenerator {

    private static String MC_CREDIT_CARD_BIN = "534913";
    private static String VISA_DEBIT_CARD_BIN = "453147";

    private static Random random = new Random(System.currentTimeMillis());

    public static String generate(CardType cardType) {

        String bin = "";
        switch (cardType){
            case CREDIT:
                bin = MC_CREDIT_CARD_BIN;
                break;
            case DEBIT:
                bin = VISA_DEBIT_CARD_BIN;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such card type:" + cardType);
        }

        int length = 16;
        int randomNumberLength = length - (bin.length() + 1);

        StringBuilder builder = new StringBuilder(bin);
        for (int i = 0; i < randomNumberLength; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
        }

        int checkDigit = getCheckDigit(builder.toString());
        builder.append(checkDigit);

        return builder.toString();
    }

    private static int getCheckDigit(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            // Get the digit at the current position.
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }
}
