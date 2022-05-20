package Util;

import java.util.*;

public class TestRandom {
    private final static Random intRandom = new Random();

    public static int getRandomInt(int min, int max){
        return intRandom.nextInt(max - min) + min;
    }

    public static Iterator getRandomUniqueNumbers(int min, int max){
        List<Integer> list = new ArrayList<>();
        for (int i = min; i <max ; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list.iterator();
    }
}
