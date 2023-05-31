package com.example.kafkalearning.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 功能描述:
 * @Param:
 * @return:
 * @Author: 徐立峰
 * 时间: 2/4/2022 10:20 AM
 */
@MapperScan("com.example.kafkalearning.mapper")
@EnableTransactionManagement// 开启事务
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        //拦截器实现
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));

        return interceptor;
    }

}
