package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomUtils {

    private static final Random random = new Random();

    public static String generateAlphabeticalString(int symbols) {
        return RandomStringUtils.randomAlphabetic(symbols);
    }

    public static String generateAlphanumericString(int symbols) {
        return RandomStringUtils.randomAlphanumeric(symbols);
    }

    public static String generateNumericString(int symbols) {
        return RandomStringUtils.randomNumeric(symbols);
    }

    public static String generateEmail() {
        String template = "%s@example.com";
        return String.format(template, generateAlphabeticalString(10));
    }

    public static Boolean randomBoolean() {
        return random.nextBoolean();
    }
}
