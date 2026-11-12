package com.kulkarni42vivek.kafka_producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;



@Configuration
public class TopicConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(TopicConfig.class);

	@Bean
	public NewTopic newTopicKafka() {
		
		logger.info("Inside TopicConfig ->>  topic created");
		
		return TopicBuilder
				.name("test_topic")
//				.partitions(1)
//				.replicas(1)
				.build();
	}
}
