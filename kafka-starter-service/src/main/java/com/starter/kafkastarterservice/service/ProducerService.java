package com.starter.kafkastarterservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.starter.kafkastarterservice.dto.Employee;
import com.starter.kafkastarterservice.kafka.KafkaProducer;

@Service
public class ProducerService {

    @Autowired
    KafkaProducer kafkaProducer;

    public void createEmployeeAndSend() throws JsonProcessingException {
        Employee e1 = new Employee();
        e1.setName("alex");
        e1.setId("300000");

        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
        final ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        final String reqString = ow.writeValueAsString(e1);

        // ObjectWriter ow2 = new ObjectMapper().writer().withDefaultPrettyPrinter();
        // String payload = ow2.writeValueAsString(e1);

        // mapper.readValue(payload, List.class);
        kafkaProducer.sendMessage("1234", reqString);
    }
}
