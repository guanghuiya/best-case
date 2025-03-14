package com.meiqiu.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

/**
 * @Description redis 配置
 * @Author sgh
 * @Date 2025/1/21
 * @Time 17:20
 */
@Configuration
public class RedisTemplateConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.password}")
    private String password;

    @Value("${spring.data.redis.database}")
    private int database;

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) throws Exception {

        RedisTemplate template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);
        //创建序列化类
        GenericToStringSerializer genericToStringSerializer = new GenericToStringSerializer(Object.class);
        template.setKeySerializer(genericToStringSerializer);
        template.setValueSerializer(genericToStringSerializer);
        return template;
    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + 6379)
//                .setPassword(password) // 如果不需要密码，可以省略这行代码
                .setDatabase(database); // 设置数据库索引，默认为0
        return Redisson.create(config);

    }
}
