package org.teamwe.carrent.config;

import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.teamwe.carrent.entity.User;

/**
 * @author FDws
 * Created on 2018/6/13 9:59
 */
@Configuration
public class InitBeans {

    private final RedisConnectionFactory factory;

    @Autowired
    public InitBeans(RedisConnectionFactory factory) {
        this.factory = factory;
    }

    @Bean("multipartResolver")
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public RedisTemplate<String, User>redisTemplate() {
        RedisTemplate<String,User> redis = new RedisTemplate<>();
        redis.setConnectionFactory(factory);
        redis.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        redis.setKeySerializer(new StringRedisSerializer());
        redis.afterPropertiesSet();
        return redis;
    }
}
