package com.taotao.cloud.sys.infrastructure.event.consumer.kafka;

import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.mq.common.base.MqConsumerBase;
import com.taotao.boot.mq.common.consumer.Acknowledgement;
import com.taotao.cloud.sys.application.service.command.UserCommandService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

//@Component
@AllArgsConstructor
public class SysKafkaConsumer extends MqConsumerBase {
	private final UserCommandService userCommandService;
	@KafkaListener(
		topics = "xxx",
		groupId = "xx-xxx-xx",
		concurrency = "3",
		errorHandler = "kafkaErrorHandler",
		containerFactory = "manualKafkaContainerFactory"
	)

	/**
	 * 列表查询
	 *
	 * @param msg 消息
	 * @param RECEIVED_KEY received_key
	 * @return 无返回值
	 * @since 2022.03
	 */
	public void listenMsg( String msg,
		@Header(KafkaHeaders.RECEIVED_KEY) String key,
		Acknowledgement ack ) {

		try {
			userCommandService.handleNotify(msg);

			//手动确认
			ack.acknowledge();
		} catch (Exception e) {
			//不确认 会自动重试
			LogUtils.info("asfd");
		}
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> manualKafkaContainerFactory(
		ConsumerFactory<String, String> consumerFactory ) {
		ConcurrentKafkaListenerContainerFactory<String, String> container = new ConcurrentKafkaListenerContainerFactory<>();
		container.setConsumerFactory(consumerFactory);
		container.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
		return container;
	}

	@Bean

	/**
	 * kafkaErrorHandler 方法
	 *
	 * @return ConsumerAwareListenerErrorHandler
	 * @since 2022.03
	 */
	public ConsumerAwareListenerErrorHandler kafkaErrorHandler() {
		return ( message,exception, consumer ) -> {
			System.out.println(message);
			System.out.println(exception);
			System.out.println(consumer);
			//可以选择重试 进入死信队列
			return null;
		};
	}
}
