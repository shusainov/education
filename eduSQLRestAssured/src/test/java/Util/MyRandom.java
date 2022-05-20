package Util;

import java.util.Random;

public class MyRandom {
    private static final Random random = new Random();

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public static String getDoubleNumber(int bound) {
        String randomNumber = Integer.toString(nextInt(bound));
        return randomNumber + randomNumber;
    }
}
