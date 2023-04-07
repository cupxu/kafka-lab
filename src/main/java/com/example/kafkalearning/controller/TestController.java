package com.example.kafkalearning.controller;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 * @description: 网络测试
 * @author: cupxu
 * @create: 2023-01-28 14:12
 **/
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private AdminClient adminClient;

    @RequestMapping("/undertow")
    public String netWorkTest() {
        return "true";
    }

    @GetMapping("/topic-info")
    public void hello() throws ExecutionException, InterruptedException{
        DescribeTopicsResult result = adminClient.describeTopics(
                Arrays.asList("my-topic1"));
        result.allTopicNames().get().forEach((k, v)->System.out.println("k: "+k+" ,v: "+v.toString()+"\n"));
    }
}
