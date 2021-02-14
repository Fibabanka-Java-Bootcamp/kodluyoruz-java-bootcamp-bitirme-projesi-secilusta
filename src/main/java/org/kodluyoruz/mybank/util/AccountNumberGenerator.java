package org.kodluyoruz.mybank.util;

import org.kodluyoruz.mybank.model.enumeration.CardType;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;


public class AccountNumberGenerator {

    private static Random random = new Random(System.currentTimeMillis());

    private static int min = 10000;
    private static int max = 99999;

    public static Long generate() {
        Long randomNumber = Long.valueOf(random.nextInt(max-min)+min);
        return randomNumber;
    }
}
