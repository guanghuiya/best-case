package com.meiqiu.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author sgh
 * @Date 2025/2/26
 * @Time 02:03
 */
@Configuration
public class RabbitMQConfig {


    @Value("${spring.rabbitmq.host:localhost}")
    private String host;

    @Value("${spring.rabbitmq.port:5672}")
    private int port;

    @Value("${spring.rabbitmq.username:guest}")
    private String username;

    @Value("${spring.rabbitmq.password:guest}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);

//        //设置通道缓存大小
//        connectionFactory.setChannelCacheSize(10);
//        // 设置通道检出超时时间（毫秒），如果在指定时间内无法获取通道，会抛出异常
//        connectionFactory.setChannelCheckoutTimeout(5000);
//        // 设置缓存模式，默认为 CHANNEL，即缓存通道
//        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
//
//        // 开启 Confirm 确认模式
//        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 设置 ConfirmCallback
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("消息发送成功，correlationData: " + correlationData);
            } else {
                System.out.println("消息发送失败，原因: " + cause);
                //消息重发
            }
        });
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }
}
