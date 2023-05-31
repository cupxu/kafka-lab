package com.example.kafkalearning.util;

/**
 * @description:
 * @author: cupxu
 * @create: 2023-05-06 10:33
 **/
public class AssginMatrix {

    public static void main(String[] args) {
        // KB 512 1024 2018
        int[] arr = new int[]{512,1024,1536};
        for (int a : arr) {
            System.out.println(Math.sqrt(a*1024/4));
        }
    }
}
