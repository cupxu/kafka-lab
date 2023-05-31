package com.example.kafkalearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author LifengXu
 */
@SpringBootApplication
@EnableScheduling
@EnableKafka
public class KafkaLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaLearningApplication.class, args);
    }

}
