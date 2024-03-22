package com.starter.kafkastarterservice.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Value("${spring.application.kafka.producer.topic}")
    private String producerTopic;

    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String key, String message) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("key", key);

            Message<Object> payload = MessageBuilder.withPayload((Object) message)
                    .setHeader(KafkaHeaders.TOPIC, producerTopic).build();

            //topic in message header
            CompletableFuture<SendResult<String, Object>> response = kafkaTemplate.send(payload);
            logger.info("message sent {}", response.get().getRecordMetadata().toString());

        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    public void sendMessage2(String key, String message) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("key", key);

            Message<Object> payload = MessageBuilder.withPayload((Object) message).build();

            //topic in method param
            CompletableFuture<SendResult<String, Object>> response = kafkaTemplate.send(producerTopic, payload);
            logger.info("message sent {}", response.get().getRecordMetadata().toString());

        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}
