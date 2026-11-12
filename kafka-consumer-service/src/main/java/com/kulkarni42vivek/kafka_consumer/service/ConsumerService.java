package com.kulkarni42vivek.kafka_consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
	
	private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);
	
	@KafkaListener(
		topics = "test_topic",
		groupId = "myGroup"
	)
	public void consume(String eventMsg) {
		log.info("Consumer log ->> " + eventMsg);
	}
}
