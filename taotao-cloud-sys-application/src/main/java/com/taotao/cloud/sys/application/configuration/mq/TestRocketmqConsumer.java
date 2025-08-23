//package com.taotao.cloud.sys.application.configuration.mq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Component;
//
///**
// * @author woodwhales on 2025-07-24 22:52
// */
//@Slf4j
//@Component
//@RocketMQMessageListener(
//        nameServer = "${rocketmq.name-server}",
//        consumerGroup = "taotao_cloud_sys_consumer_group",
//        topic = "taotao-cloud-sys",
//        accessKey = "taotaocloud",
//        secretKey = "taotaocloud")
//public class TestRocketmqConsumer implements RocketMQListener<MessageExt> {
//
//    @Override
//    public void onMessage(MessageExt message) {
//        String body = new String(message.getBody());
//        log.info("accept mq, topic={}, msg={}, message={}", message.getTopic(), body, message);
//    }
//}
