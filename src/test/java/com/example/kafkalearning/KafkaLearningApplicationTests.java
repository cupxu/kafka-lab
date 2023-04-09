package com.example.kafkalearning;

import com.example.kafkalearning.util.InfluxDBTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
class KafkaLearningApplicationTests {

    @Autowired
    InfluxDBTemplate influxDBTemplate;

    @Test
    void influxDBWriteTest() {
        String measurement = "test_db";
        Map<String, String> tags = new HashMap<>();
        tags.put("tag", "1");
        Map<String, Object> fields = new HashMap<>();
        fields.put("field", "limi");
        influxDBTemplate.write(measurement,tags, fields);
    }

    @Test
    void setInfluxDBReadTest() {
        log.info("influxdb–≈œ¢,{}", influxDBTemplate.query("select * from test_db"));
    }

}
