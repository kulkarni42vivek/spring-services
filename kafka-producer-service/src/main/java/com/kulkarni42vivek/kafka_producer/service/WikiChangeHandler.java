package com.kulkarni42vivek.kafka_producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;

public class WikiChangeHandler implements EventHandler {
	
	private KafkaTemplate<String, String> kafkaTemplate;
	private String topic;
	private Logger logger = LoggerFactory.getLogger(WikiChangeHandler.class);
	
	public WikiChangeHandler(KafkaTemplate<String, String> kafkaTemplate,String topic) {
		this.kafkaTemplate = kafkaTemplate;
		this.topic = topic;
	}

	@Override
	public void onOpen() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClosed() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(String event, MessageEvent messageEvent) throws Exception {
		logger.info("WikiChangeHandler ->> "+messageEvent.getData());
		this.kafkaTemplate.send(this.topic , messageEvent.getData());
	}

	@Override
	public void onComment(String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
