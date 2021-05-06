package com.example.android.mobileperf.compute;

import java.util.Random;


public class SampleData {
    public static Integer[] coolestRandomNumbers = new Integer[3000];
    static {
        Random randomGenerator = new Random();
        for (int i=0; i<3000; i++) {
            coolestRandomNumbers[i] = randomGenerator.nextInt();
        }
    }
}
