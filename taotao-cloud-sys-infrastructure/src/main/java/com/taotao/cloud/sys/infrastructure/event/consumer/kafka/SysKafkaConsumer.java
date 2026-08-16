package com.taotao.cloud.sys.infrastructure.event.consumer.kafka;

import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.mq.common.base.MqConsumerBase;
import com.taotao.cloud.sys.application.dto.app.command.NotifyAppCommand;
import com.taotao.cloud.sys.application.service.command.AppCommandService;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SysKafkaConsumer extends MqConsumerBase {

	private final AppCommandService appCommandService;


	/**
	 * 列表查询
	 *
	 * @param RECEIVED_TOPIC received_topic
	 * @return 无返回值
	 * @since 2022.03
	 */
	@KafkaListener(
		topics = "sys-topic",
		groupId = "sys_consumer_group",
		concurrency = "3"
//		errorHandler = "kafkaErrorHandler",
//		containerFactory = "manualKafkaContainerFactory"
	)
	public void listenMsg(
//		@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
//		@Header(KafkaHeaders.RECEIVED_KEY) String key,
//		@Payload String msg,
		ConsumerRecord<String, String> record,
		Acknowledgment ack ) {

		try {
			String msg = record.value();

			NotifyAppCommand notifyGoodsCommand = from(msg, NotifyAppCommand.class);

			handleNotify(() -> {

				appCommandService.handleNotify(notifyGoodsCommand);

			});

		} catch (Exception e) {
			if(e instanceof BusinessException businessException){
				//手动确认
				ack.acknowledge();
			}else {
				throw new BusinessException(e);
			}
		}
		//手动确认
		ack.acknowledge();
	}

//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, String> manualKafkaContainerFactory(
//		ConsumerFactory<String, String> consumerFactory ) {
//		ConcurrentKafkaListenerContainerFactory<String, String> container = new ConcurrentKafkaListenerContainerFactory<>();
//		container.setConsumerFactory(consumerFactory);
//		container.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
//		return container;
//	}

//	@Bean
//	public ConsumerAwareListenerErrorHandler kafkaErrorHandler() {
//		return ( message, exception, consumer ) -> {
//			//可以选择重试 进入死信队列
//			return null;
//		};
//	}

}
