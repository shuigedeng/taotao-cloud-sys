package com.taotao.cloud.sys.application.configuration.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author woodwhales on 2025-07-24 22:52
 */
@Slf4j
@Component
public class TestKafkaConsumer {

	/**
	 * @KafkaListener(groupId = "testGroup", topicPartitions = {
	 *             @TopicPartition(topic = "topic1", partitions = {"0", "1"}),
	 *             @TopicPartition(topic = "topic2", partitions = "0",
	 *                     partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
	 *     },concurrency = "6")
	 *  //concurrency就是同组下的消费者个数，就是并发消费数，必须小于等于分区总数
	 * @param record
	 */
	@KafkaListener(topics = "taotao-cloud-sys")
	public void onMessage(@Payload String message,  // 消息体
		@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
		@Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
		@Header(KafkaHeaders.OFFSET) long offset,
		Acknowledgment ack){
		try {
			//insertIntoDb(buffer);//这里为插入数据库代码
			//System.out.println("message: " + message);
			System.out.println("简单消费，record："+topic+"-"+partition+"-"+message);
			ack.acknowledge();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
