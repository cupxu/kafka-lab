package com.example.kafkalearning;


import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.QueryApi;
import com.influxdb.client.domain.Query;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
class KafkaLearningApplicationTests {

    @Autowired
    InfluxDBClient influxDBClient;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void redisTest() {
        System.out.println(redisTemplate.opsForSet().size("mySet"));
    }


}
