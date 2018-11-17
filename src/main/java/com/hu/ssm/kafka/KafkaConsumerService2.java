package com.hu.ssm.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

/**
 * @author hutiantian
 * @create 2018/11/10 14:37
 * @since 1.0.0
 */
public class KafkaConsumerService2 implements MessageListener<String, Object> {

	@Override
	public void onMessage(ConsumerRecord<String, Object> record) {
		System.out.println("消费者2接受到信息:"+record.value().toString());
	}


}
