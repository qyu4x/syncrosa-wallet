package com.syncrosa.loansservice.helper;

import java.security.SecureRandom;

public class GenerateRandomID {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String generateRandomStringId(Integer size) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder idRandom = new StringBuilder();
        for (int i = 0; i < size; i++) {
            idRandom.append(CHARACTERS.charAt(secureRandom.nextInt(CHARACTERS.length())));
        }
        return idRandom.toString();
    }

}
