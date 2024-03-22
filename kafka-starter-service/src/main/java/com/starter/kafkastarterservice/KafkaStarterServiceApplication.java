package com.starter.kafkastarterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.starter.kafkastarterservice.service.ProducerService;

@SpringBootApplication
public class KafkaStarterServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KafkaStarterServiceApplication.class, args);
		ProducerService producerService = context.getBean(ProducerService.class);
		try {
			producerService.createEmployeeAndSend();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}
