package com.api.utils;

import com.github.javafaker.Faker;

public class DataGeneratorUtils {

    private static final Faker faker = new Faker();

    public static String generateRandomId() {
        return faker.idNumber().valid();
    }
}
