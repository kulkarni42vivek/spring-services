package com.kulkarni42vivek.kafka_producer.service;


import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

@Service
public class ProducerService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);
	private  final KafkaTemplate<String, String> kafkaTemplate;
	
	ProducerService(KafkaTemplate<String, String> kafkaTemple){
		this.kafkaTemplate = kafkaTemple;
	}
	
	public void produceKafka() throws InterruptedException {
		String URL = "https://stream.wikimedia.org/v2/stream/recentchange";
//		EventHandler eventHandler = new WikiChangeHandler(kafkaTemplate, "test_topic");
//		EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(URL) );
//		EventSource eventSource =  builder.build();
//		eventSource.start();
		
//		TimeUnit.MINUTES.sleep(10);
		
		this.kafkaTemplate.send("test_topic" , "test");
		
	}
}
