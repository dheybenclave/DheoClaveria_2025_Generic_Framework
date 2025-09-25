package utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class Utilities {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static int STRING_LENGTH = 10; // Length of the random string
    private static final Random RANDOM = new SecureRandom();

    public String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    public int generateRandomInteger(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public String generateRandomStringInteger(int strLength) {

        STRING_LENGTH = strLength >= 0 ? STRING_LENGTH : strLength;
        StringBuilder stringBuilder = new StringBuilder(STRING_LENGTH);

        // Generate random string
        for (int i = 0; i < STRING_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }

        // Generate random integer
        int randomInteger = RANDOM.nextInt(1000); // You can specify the range as needed

        // Combine string and integer
        return stringBuilder.toString() + randomInteger;
    }
}
