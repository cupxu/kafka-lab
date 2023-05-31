package com.example.kafkalearning.config;

/**
 * @description:
 * @author: cupxu
 * @create: 2023-04-07 11:15
 **/
import com.influxdb.LogLevel;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
/**
 * 时序数据库配置
 */
@Configuration
public class InfluxdbConfig {

    @Value("${spring.influx.url:''}")
    private String influxDBUrl;

    @Value("${spring.influx.token:''}")
    private String token;
    @Value("${spring.influx.org:''}")
    private String org;

    @Value("${spring.influx.bucket:''}")
    private String bucket;

    @Bean
    public InfluxDBClient influxDBClient() {
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(influxDBUrl, token.toCharArray(), org, bucket);
        // influxDBClient.setLogLevel(LogLevel.BODY);
        return influxDBClient;
    }
}

