package com.hu.ssm.kafka;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * kafka消息序列化类
 * @author hutiantian
 * @date: 2018/11/17 11:00
 * @since 1.0.0
 */
@Slf4j
public class ObjectSerializer implements Serializer<Object> {

    public void configure(Map<String, ?> configs, boolean isKey) {
    }


    public byte[] serialize(String topic, Object data) {
        try{
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            @Cleanup ObjectOutputStream oos  = new ObjectOutputStream(bao);
            oos.writeObject(data);
            return bao.toByteArray();
        }catch (Exception e){
            log.debug("kafka序列化失败！",e);
        }
        return null;
    }

    public void close() {
    }
}
