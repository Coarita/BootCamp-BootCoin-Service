package com.nttdata.bootcamp.bootcoinservice.infrastructure.spring.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.nttdata.bootcamp.bootcoinservice.domain.BootCoin;

@Configuration
public class RedisConfiguration {
	
	@Bean
	ReactiveRedisOperations<String, BootCoin> redisOperations(ReactiveRedisConnectionFactory factory) {
		Jackson2JsonRedisSerializer<BootCoin> serializer = new Jackson2JsonRedisSerializer<>(BootCoin.class);
		RedisSerializationContext.RedisSerializationContextBuilder<String, BootCoin> builder = RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
		RedisSerializationContext<String, BootCoin> context = builder.value(serializer).build();
		ReactiveRedisTemplate<String, BootCoin> redis = new ReactiveRedisTemplate<>(factory, context);
		redis.expire("*", Duration.ofMinutes(5));
		return redis;
	}

}
