package com.example.kafkalearning.service;

import com.example.kafkalearning.mapper.MetricsMapper;
import com.example.kafkalearning.pojo.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @description: 获取redis结果
 * @author: cupxu
 * @create: 2023-05-06 08:52
 **/
@Slf4j
@Service
public class DataPointAcquire {


    @Autowired
    MetricsMapper metricsMapper;

    @Autowired
    Metrics metric;

    @Autowired
    RedisTemplate redisTemplate;

    @PostConstruct
    @Order(2)
    @Scheduled(fixedRate = 1000)
    public void writeDataPoint() {
        try {
            metric.setDataSize((double) 512);
            metric.setNodeNums(48);
            metric.setValidTime((double) 100);
            int messageSent = (int) redisTemplate.opsForValue().get("producer");
            int messageConsumed = (int) redisTemplate.opsForValue().get("consumer");
            double consumedRate = (double) messageConsumed / messageSent;
            metric.setMessageSent(messageSent);
            metric.setMessageConsumed(messageConsumed);
            metric.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            metric.setSuccessRate(consumedRate);
            metricsMapper.insert(metric);
            log.info("metric: {}",metric.toString());
        } catch (NullPointerException e){
            log.error("数据还没写进redis");
        }
    }
}
