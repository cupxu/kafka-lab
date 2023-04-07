package com.example.kafkalearning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: cupxu
 * @create: 2023-02-15 20:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MyTopic {
    private String name;
    private Integer numPartitions;
    private Short replicationFactor;
}
