package com.taotao.cloud.sys.application.configuration.mq;


import com.alibaba.fastjson2.JSON;
import com.taotao.boot.common.utils.log.LogUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class TestKafkaProducer implements CommandLineRunner {

	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("kafka应用启动完成，执行初始化操作...");
		Map<String, Object> body = new HashMap<>();
		body.put("uuid", UUID.randomUUID().toString());
		try {
			CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("taotao-cloud-sys", JSON.toJSONString(body));
			future.whenComplete((result, ex) -> {
				if (ex != null) {
					// 发送失败处理
					System.err.println("消息发送失败: " + ex.getMessage());
				} else {
					// 发送成功处理
					System.out.println("消息发送成功，Topic: " + result.getRecordMetadata().topic());
					System.out.println("消息发送成功，Partition: " + result.getRecordMetadata().partition());
					System.out.println("消息发送成功，Offset: " + result.getRecordMetadata().offset());
				}
			});
		} catch (Exception e) {
			LogUtils.error("send msg error, topic={}, errorMsg={}", "taotao-cloud-sys", e.getMessage(), e);
		}

	}
}
