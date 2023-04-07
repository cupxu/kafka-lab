package com.example.kafkalearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * @author LifengXu
 */
@SpringBootApplication
@EnableKafka
public class KafkaLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaLearningApplication.class, args);
    }

}
