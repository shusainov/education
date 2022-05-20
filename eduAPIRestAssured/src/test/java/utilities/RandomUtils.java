package utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
    public static String getAlphabetic(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }
}
