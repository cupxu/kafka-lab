package com.example.kafkalearning.service;

import cn.hutool.json.JSONObject;
import com.example.kafkalearning.mapper.MetricsMapper;
import com.example.kafkalearning.pojo.Metrics;
import com.example.kafkalearning.pojo.Pojo;
import com.example.kafkalearning.util.MD5Check;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @description:
 * @author: cupxu
 * @create: 2023-02-15 11:40
 **/
@Service
@Slf4j
public class ConsumerService {

    @Autowired
    InfluxDBClient influxDBClient;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 消费监听
     */
    @KafkaListener(id = "my-consumer", groupId = "my-group", topics = "my-topic")
    public void listen(ConsumerRecord<String, Object> record, Acknowledgment acknowledgment) {
        log.info("Listening success!");
        if (record.value() != null) {
            long curTime = System.currentTimeMillis();
            JSONObject json = (JSONObject) record.value();
            Pojo pojo = json.toBean(Pojo.class);
            // 存在的话
            redisTemplate.opsForValue().increment("consumer");
            long timeConsumed = curTime - pojo.getTimestamp();
            String uuid = pojo.getUuid();
            WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
            if (!pojo.getMd5Hash().equals(MD5Check.getMD5Hash(pojo.getMatrix()))) {
                log.error("md5check result : not match");
                // Create a new point with measurement "temperature"
                Point point = Point.measurement("fail")
                        .addTag("uuid",uuid)
                        .addField("timeConsumed", timeConsumed)
                        .time(Instant.now().toEpochMilli(), WritePrecision.MS);;
                // Write the point to InfluxDB
                writeApi.writePoint(point);
            }else {
                log.info("md5check match time consumed {}", timeConsumed);
                // Create a new point with measurement "temperature"
                Point point = Point.measurement("success")
                        .addTag("uuid",uuid)
                        .addField("timeConsumed", timeConsumed)
                        .time(Instant.now().toEpochMilli(), WritePrecision.MS);;
                // Write the point to InfluxDB
                writeApi.writePoint(point);
            }
            acknowledgment.acknowledge();

        }
    }
}
