//package com.taotao.cloud.sys.application.configuration.mq;
//
//import com.alibaba.fastjson2.JSON;
//import com.taotao.boot.common.utils.log.LogUtils;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TestRocketmqProducer implements CommandLineRunner {
//
//	@Autowired
//	private RocketMQTemplate rocketMQTemplate;
//
//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("应用启动完成，执行初始化操作...");
//		Map<String, Object> body = new HashMap<>();
//		body.put("uuid", UUID.randomUUID().toString());
//		try {
//			SendResult sendResult = rocketMQTemplate.syncSend("taotao-cloud-sys", JSON.toJSONString(body));
//			LogUtils.info("send msg success, topic={}, sendResult={}", "taotao-cloud-sys", sendResult);
//		} catch (Exception e) {
//			LogUtils.error("send msg error, topic={}, errorMsg={}", "taotao-cloud-sys", e.getMessage(), e);
//		}
//
//	}
//}
