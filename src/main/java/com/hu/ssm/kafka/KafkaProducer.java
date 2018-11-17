package com.hu.ssm.kafka;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;


/**
 * @author hutiantian
 * @create 2018/11/10 14:37
 * @since 1.0.0
 */

@Api(tags = "kafka producer")
@Controller
@RequestMapping("/producer")
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	@PostMapping("/send")
	@ApiOperation(value = "kafka消息提供者", notes = "kafka消息提供者", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String sendMessgae() throws Exception{
		for(int i = 0 ;i<20;i++){
			KafkaMessage message = new KafkaMessage();
			message.setName("miaomiao"+i);
			message.setAge(i);
			kafkaTemplate.send("myTopic", message);
		}
		return "发送成功";
	}

}
