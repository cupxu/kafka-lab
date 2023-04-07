package com.example.kafkalearning.controller;

import com.example.kafkalearning.entity.Book;
import com.example.kafkalearning.service.BookProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @description:
 * @author: cupxu
 * @create: 2023-02-15 11:49
 **/
@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Value("${kafka.topic.my-topic1}")
    String myTopic1;
    @Value("${kafka.topic.my-topic2}")
    String myTopic2;
    private final BookProducerService producer;
    private AtomicLong atomicLong = new AtomicLong();

    BookController(BookProducerService producer) {
        this.producer = producer;
    }

    @PostMapping("/test")
    public void sendMessageToKafkaTopic(@RequestParam("name") String name) {
        this.producer.sendMessage(myTopic1, new Book(atomicLong.addAndGet(1), name));
        this.producer.sendMessage(myTopic2, new Book(atomicLong.addAndGet(1), name));
    }
}
