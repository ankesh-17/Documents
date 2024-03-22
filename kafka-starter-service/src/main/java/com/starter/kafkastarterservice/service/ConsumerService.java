package com.starter.kafkastarterservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.starter.kafkastarterservice.dto.Employee;

@Service
public class ConsumerService {

    final static Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    public void printEmployee(String key, String payload) throws JsonProcessingException {

        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
        
        Employee employee = mapper.readValue(payload, Employee.class);

        LOGGER.info(" " + employee);
    }
}
