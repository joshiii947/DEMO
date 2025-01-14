package com.allica.demo.common;

import java.util.Random;
import java.util.UUID;

public class Constants {

    public static final int ACCOUNT_NUMBER_LENGTH = 10;

    // generates random number
    public static String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(random.nextInt(9) + 1);
        for (int i = 1; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    // generates random customerId
    public static String generateRandomCustomerId(String name){
        String sanitizedName = name.toUpperCase().replaceAll("\\s+", "");

        // Generate a UUID and take only a part of it for uniqueness
        String randomPart = UUID.randomUUID().toString().substring(0, 8);

        // Combine the name and random part to form the ID
        return sanitizedName + "_" + randomPart;
    }
}
