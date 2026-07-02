package com.taotao.cloud.sys.infrastructure.event.consumer.roketmq;

import com.taotao.boot.mq.common.base.MqConsumerBase;
import com.taotao.cloud.sys.application.service.command.UserCommandService;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQListener;

import java.nio.charset.StandardCharsets;

//@Component
//@RocketMQMessageListener(
//	topic = "${taotao.data.rocketmq.member-topic}",
//	selectorExpression = " res || xx",
//	consumerGroup = "${taotao.data.rocketmq.member-group}",
//	consumeMode = ConsumeMode.ORDERLY,
//	messageModel = MessageModel.BROADCASTING
//)
@AllArgsConstructor
public class SysRocketmqConsumer extends MqConsumerBase implements RocketMQListener<MessageExt> {

	private final UserCommandService userCommandService;

	//没有抛异常 自动确认
	//抛异常
	@Override
	public void onMessage( MessageExt message ) {
		try {
			String s = new String(message.getBody(), StandardCharsets.UTF_8);
			//手动确认
			userCommandService.handleNotify(s);
		} catch (Exception e) {

		}
	}


}
