package com.hu.ssm.kafka;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "kafka producer")
@Controller
@RequestMapping("/producer")
/**
 * @author hutiantian
 * @create 2018/11/10 14:37
 * @since 1.0.0
 */
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@PostMapping("/send")
	@ApiOperation(value = "kafka消息提供者", notes = "kafka消息提供者", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String sendMessgae(){
		for(int i = 0 ;i<20;i++){
			kafkaTemplate.send("myTopic", "i am handsame"+i);
		}
		return "发送成功";
	}
}
