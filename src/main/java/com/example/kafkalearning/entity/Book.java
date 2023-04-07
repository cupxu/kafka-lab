package com.example.kafkalearning.entity;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @description: 消息实体类
 * @author: cupxu
 * @create: 2023-02-15 11:30
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Repository
@ToString
@Scope("prototype")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
}
