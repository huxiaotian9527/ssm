package com.hu.ssm.kafka;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * kafka对象消息类
 * @author hutiantian
 * @date: 2018/11/17 10:15
 * @since 1.0.0
 */
@Data
@ToString
public class KafkaMessage implements Serializable {
    private String name;
    private int age;
}
