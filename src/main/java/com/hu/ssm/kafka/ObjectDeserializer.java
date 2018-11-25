package com.hu.ssm.kafka;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * kafka消息反序列化工具
 * @author hutiantian
 * @date: 2018/11/17 11:01
 * @since 1.0.0
 */
@Slf4j
public class ObjectDeserializer implements Deserializer<Object> {

    public void configure(Map<String, ?> var1, boolean var2){

    }

    public  Object deserialize(String var1, byte[] var2){
        try{
            @Cleanup ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(var2));
            return ois.readObject();
        }catch (Exception e){
            log.error("kafka反序列化失败！",e);
        }
        return null;
    }

    public void close(){

    }
}
