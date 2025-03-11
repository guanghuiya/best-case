package com.meiqiu.bestCaseV1.消息队列;

import com.meiqiu.base.MyConstant;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * @Description 消费者
 * @Author sgh
 * @Date 2025/2/24
 * @Time 14:20
 */
@Component
public class Receiver {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //执行消息队列，开启手动消费
    @RabbitListener(queues = "myQueue", ackMode = "MANUAL") // 指定队列名称，这里是"myQueue"
    public void receive(Message message, Channel channel) throws IOException, TimeoutException {
        String messageId = message.getMessageProperties().getMessageId();
        if (redisTemplate.opsForValue().get(MyConstant.REDIS_MESSAGE_KEY_PREFIX + messageId) != null) {
            System.out.println("消息重复消费，messageId: " + messageId);
            return;
        }
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            // 处理消息的业务逻辑
            String msg = new String(message.getBody());
            System.out.println("Received message: " + msg);
            // 手动确认消息
            channel.basicAck(deliveryTag, false);
            System.out.println("Message acknowledged");
            // 将消息ID保存到Redis中，用于标记消息已处理,并且设置过期时间为 60分钟，防止内存占用过多
            redisTemplate.opsForValue().set(MyConstant.REDIS_MESSAGE_KEY_PREFIX + messageId, "true", 60, TimeUnit.MINUTES);
        } catch (Exception e) {
            // 处理消息失败，拒绝消息并重新入队
            channel.basicNack(deliveryTag, false, true);
            System.out.println("Message nacked and requeued");
        }
    }
}
