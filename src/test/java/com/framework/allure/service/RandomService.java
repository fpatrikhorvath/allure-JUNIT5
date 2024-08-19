package com.framework.allure.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class RandomService {
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private final Random random = new SecureRandom();

    public String getRandomString(final int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }
}
