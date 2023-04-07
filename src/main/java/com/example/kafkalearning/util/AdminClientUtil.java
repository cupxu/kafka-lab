package com.example.kafkalearning.util;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 注册 AdminClient 这个 Bean
 * @author: cupxu
 * @create: 2023-02-15 20:04
 **/
@Configuration
public class AdminClientUtil {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServers;


    @Bean
    public AdminClient adminClient() {
        Map<String, Object> props = new HashMap<>();
        //配置Kafka实例的连接地址
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
        return AdminClient.create(props);
    }
}
