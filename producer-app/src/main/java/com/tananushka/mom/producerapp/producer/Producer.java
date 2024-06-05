package com.tananushka.mom.producerapp.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class Producer {
    private final JmsTemplate jmsTemplate;
    @Value("${spring.jms.topic-name}")
    private String topicName;

    @Scheduled(fixedRateString = "${spring.jms.scheduler.fixed-rate}")
    public void publishMessage() {
        String messageContent = String.valueOf(LocalDateTime.now());
        jmsTemplate.convertAndSend(new ActiveMQTopic("VirtualTopic." + topicName), messageContent, message -> {
            message.setJMSCorrelationID(UUID.randomUUID().toString());
            log.info("Message with ID: {} published to {}", message.getJMSCorrelationID(), topicName);
            return message;
        });
    }
}
