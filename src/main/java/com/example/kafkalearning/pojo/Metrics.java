package com.example.kafkalearning.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 
 * </p>
 *
 * @author xulifeng
 * @since 2023-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Repository
public class Metrics implements Serializable {

    private static final long serialVersionUID = 1L;

    private Timestamp timestamp;

    private Double validTime;

    private Integer nodeNums;

    private Double dataSize;

    private Integer messageSent;

    private Integer messageConsumed;

    private Double successRate;


}
