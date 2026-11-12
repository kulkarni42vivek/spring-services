package com.kulkarni42vivek.kafka_producer;

import com.kulkarni42vivek.kafka_producer.service.ProducerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner {

	private final ProducerService producerService;

	KafkaProducerApplication(ProducerService producerService) {
		this.producerService = producerService;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		producerService.produceKafka();
	}

}
