package com.meiqiu.bestCase.消息队列;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Description 消息队列
 * @Author sgh
 * @Date 2025/2/24
 * @Time 13:45
 */
@RestController
@RequestMapping("/mq")
public class RabbitMqController {

    @Autowired
    private Sender sender;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void send() {
        String message = "test001";
        sender.send(message);
    }

}
