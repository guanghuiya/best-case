package com.meiqiu.bestCase.消息队列;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Description
 * @Author sgh
 * @Date 2025/2/24
 * @Time 14:19
 */
@Service
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        //消息 id
        String messageId = UUID.randomUUID().toString();
        MessageProperties properties = new MessageProperties();
        properties.setMessageId(messageId);
        Message amqpMessage = new Message(message.getBytes(), properties);
        CorrelationData correlationData = new CorrelationData(messageId);
        rabbitTemplate.convertAndSend("demo","test", amqpMessage,correlationData);
    }

}
