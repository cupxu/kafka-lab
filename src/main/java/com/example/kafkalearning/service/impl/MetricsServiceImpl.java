package com.example.kafkalearning.service.impl;

import com.example.kafkalearning.pojo.Metrics;
import com.example.kafkalearning.mapper.MetricsMapper;
import com.example.kafkalearning.service.IMetricsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xulifeng
 * @since 2023-05-06
 */
@Service
public class MetricsServiceImpl extends ServiceImpl<MetricsMapper, Metrics> implements IMetricsService {

}
