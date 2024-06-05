package com.tananushka.mom.consumerapp.two.consumer;

import jakarta.jms.JMSException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Consumer {
    @Value("${spring.application.name}")
    private String consumerName;

    @JmsListener(
            destination = "Consumer.${spring.application.name}.VirtualTopic.${spring.jms.topic-name}",
            containerFactory = "queueListenerFactory"
    )
    public void receiveDurableMessage(Message message) throws JMSException {
        String jmsCorrelationID = message.getJMSCorrelationID();
        String body = message.getBody(String.class);
        log.info("Message received by Consumer.{}: jmsCorrelationID={}, sent {}", consumerName, jmsCorrelationID, body);
    }
}

