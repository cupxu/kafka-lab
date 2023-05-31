package com.example.kafkalearning.util;

/**
 * @description:
 * @author: cupxu
 * @create: 2023-04-28 11:11
 **/
import java.util.Random;

public class RandomArray {
    public static void randomFill(int[][] data, int min, int max) {
        Random rand = new Random();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = rand.nextInt(max - min + 1) + min;
            }
        }
    }
}
