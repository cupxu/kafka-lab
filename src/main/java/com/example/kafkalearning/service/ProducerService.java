package com.example.kafkalearning.service;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.kafkalearning.pojo.Pojo;
import com.example.kafkalearning.util.MD5Check;
import com.example.kafkalearning.util.RandomArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @description: 消息生产
 * @author: cupxu
 * @create: 2023-02-15 11:32
 **/
@Service
@Slf4j
public class ProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    Pojo pojo;

    @Autowired
    RedisTemplate redisTemplate;


/*    @PostConstruct
    @Order(2)
    @Scheduled(fixedRate = 1000)*/
    public void sendMessage() {
        // int 4 Byte
        //362.03867196751236
        //512.0
        //724.0773439350247
        int[][] matrix = new int[362][362];
        pojo.setUuid(UUID.randomUUID().toString());
        RandomArray.randomFill(matrix,-10000,10000);
        pojo.setMatrix(matrix);
        String md5hash = MD5Check.getMD5Hash(matrix);
        pojo.setMd5Hash(md5hash);
        pojo.setTimestamp(System.currentTimeMillis());
        redisTemplate.opsForValue().increment("producer");
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("my-topic", JSONUtil.parse(pojo));
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> sendResult) {
                log.info("生产者成功发送消息到my-topic");
            }
            @Override
            public void onFailure(Throwable throwable) {
                log.error("生产者发送消息失败，原因：{}",throwable.getMessage());
            }
        });
    }
}
