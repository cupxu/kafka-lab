package com.example.kafkalearning.service;


import com.example.kafkalearning.entity.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: cupxu
 * @create: 2023-02-15 11:40
 **/
@Service
public class BookConsumerService {

    @Value("${kafka.topic.my-topic1}")
    private String myTopic1;
    @Value("${kafka.topic.my-topic2}")
    private String myTopic2;
    private final Logger logger = LoggerFactory.getLogger(BookProducerService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 消费监听
     */
    @KafkaListener(id = "consumer1", groupId = "group1", topics = "my-topic1")
    public void listen1(ConsumerRecord<String, Object> record) {
        System.out.println(record);
    }
    /**
     * 消费监听
     */
    @KafkaListener(id = "consumer2", groupId = "group1", topics = "my-topic2")
    public void listen2(ConsumerRecord<String, Object> record) {
        logger.info("kafka processMessage start");
        logger.info("processMessage, topic = {}, msg = {}", record.topic(), record.value());
        Book book = (Book) record.value();
        System.out.println(book.getId());

        // do something ...

        logger.info("kafka processMessage end");

    }
}
