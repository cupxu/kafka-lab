package com.example.kafkalearning.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.common.requests.DescribeConfigsResponse.ConfigSource.TOPIC_CONFIG;

/**
 * @description:
 * @author: cupxu
 * @create: 2023-02-15 20:31
 **/
@Configuration
public class KafkaTopicConfig {
    @Value("${kafka.topic.my-topic}")
    String myTopic;

    /**
     * JSON消息转换器
     */
    @Bean
    public RecordMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

    /**
     * 通过注入一个 NewTopic 类型的 Bean 来创建 topic，如果 topic 已存在，则会忽略。
     */
    @Bean
    public NewTopic myTopic() {
        Map<String, String> configs = new HashMap<>();
        // Set retention time to 1 day
        NewTopic newTopic = new NewTopic("my-topic", 5, (short) 3)
                .configs(configs);
        return newTopic;
    }
}

