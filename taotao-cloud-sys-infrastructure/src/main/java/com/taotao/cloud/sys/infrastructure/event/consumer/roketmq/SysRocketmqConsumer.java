package com.taotao.cloud.sys.infrastructure.event.consumer.roketmq;

import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.mq.common.base.MqConsumerBase;
import com.taotao.cloud.sys.application.dto.app.command.NotifyAppCommand;
import com.taotao.cloud.sys.application.service.command.AppCommandService;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.client.annotation.RocketMQMessageListener;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.message.MessageView;
import org.apache.rocketmq.client.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RocketMQMessageListener(
	topic = "sys-topic",
	tag = "*",
	sslEnabled = false,
	consumerGroup = "sys_consumer_group"
)
@AllArgsConstructor
public class SysRocketmqConsumer extends MqConsumerBase implements RocketMQListener {
	private final AppCommandService appCommandService;

	@Override
	public ConsumeResult consume( MessageView messageView ) {
		try {
			String msg = StandardCharsets.UTF_8.decode(messageView.getBody()).toString();

			NotifyAppCommand notifyGoodsCommand = from(msg, NotifyAppCommand.class);

			handleNotify(() -> {

				appCommandService.handleNotify(notifyGoodsCommand);

			});

		} catch (Exception e) {
			if(e instanceof BusinessException businessException){
				return ConsumeResult.SUCCESS;
			}

			return ConsumeResult.FAILURE;
		}
		return ConsumeResult.SUCCESS;
	}
}
