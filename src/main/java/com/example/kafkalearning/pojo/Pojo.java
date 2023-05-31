package com.example.kafkalearning.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: cupxu
 * @create: 2023-04-28 10:39
 **/

@Getter
@Component
@ToString
@NoArgsConstructor
public class Pojo {

    String uuid;
    long timestamp;

    int[][] matrix;

    String md5Hash;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void setMd5Hash(String md5Hash) {
        this.md5Hash = md5Hash;
    }
}
