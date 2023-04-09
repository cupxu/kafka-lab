package com.example.kafkalearning.config;

/**
 * @description:
 * @author: cupxu
 * @create: 2023-04-07 11:15
 **/
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("spring.influx")
public class InfluxdbProperties {
    private String url;
    private String username;
    private String password;
    private String database;
    private String retention;//保留策略
}

