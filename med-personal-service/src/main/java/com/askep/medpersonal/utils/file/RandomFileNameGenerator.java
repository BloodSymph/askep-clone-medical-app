package com.askep.medpersonal.utils.file;

import lombok.experimental.UtilityClass;

import java.util.Random;

import static java.lang.Math.random;


@UtilityClass
public class RandomFileNameGenerator {

    private static final int LEFT_LIMIT = 97;

    private static final int RIGHT_LIMIT = 122;

    private static final int LIMIT = 10;

    private final Random random = new Random();

    public static String generateRandomFileName() {
        return random.ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
                .limit(LIMIT)
                .collect(
                        StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append
                ).toString();
    }

}
