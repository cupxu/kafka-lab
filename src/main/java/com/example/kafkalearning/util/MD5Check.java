package com.example.kafkalearning.util;

/**
 * @description:
 * @author: cupxu
 * @create: 2023-04-28 10:50
 **/
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;

public class MD5Check {
    public static String getMD5Hash(int[][] data) {
        try {
            // 将二维数组转换为字节数组
            ByteBuffer buffer = ByteBuffer.allocate(data.length * data[0].length * 4);
            for (int[] row : data) {
                for (int value : row) {
                    buffer.putInt(value);
                }
            }
            byte[] bytes = buffer.array();

            // 计算MD5哈希值
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            byte[] hash = md.digest();

            // 将哈希值转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}