package com.starter.kafkastarterservice.kafka;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.starter.kafkastarterservice.service.ConsumerService;

@Component
public class KafkaConsumer {

    Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private ConsumerService consumerService;

    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = {
            "${spring.application.kafka.consuner.topic}" }, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Message<Object> message) {
        try {

            String payload = (String) message.getPayload();
            Map<String, Object> headers = message.getHeaders();
            String key = (String) headers.get("key");

            consumerService.printEmployee(key, payload);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}