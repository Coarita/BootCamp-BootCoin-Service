package com.nttdata.bootcamp.bootcoinservice.infrastructure.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nttdata.bootcamp.bootcoinservice.application.BootCoinOperations;
import com.nttdata.bootcamp.bootcoinservice.application.impl.BootCoinOperationsImpl;

@Configuration
public class SpringConfiguration {
	
	@Bean
	public BootCoinOperations operations() {
		return new BootCoinOperationsImpl();
	}

}
