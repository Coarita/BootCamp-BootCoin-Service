package com.nttdata.bootcamp.bootcoinservice.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.bootcoinservice.application.BootCoinOperations;
import com.nttdata.bootcamp.bootcoinservice.domain.BootCoin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BootCoinOperationsRedisImpl implements BootCoinOperations {

	@Autowired
	ReactiveRedisOperations<String, BootCoin> bootcoinOperations;
	
	@Override
	public Flux<BootCoin> queryAll() {
		return bootcoinOperations.keys("*")
				.flatMap(bootcoinOperations.opsForValue()::get);
	}

	@Override
	public Mono<BootCoin> findId(String identityNumber) {
		return bootcoinOperations.opsForValue()
				.get(identityNumber)
				.doOnNext(bootcoin->bootcoinOperations.opsForValue()
						.set(identityNumber, bootcoin));
	}

	@Override
	public Mono<BootCoin> create(BootCoin bootcoin) {
		return bootcoinOperations.opsForValue()
				.set(bootcoin.getIdentityNumber(), bootcoin)
				.thenReturn(bootcoin.getIdentityNumber())
				.flatMap(bootcoinOperations.opsForValue()::get);
	}

	@Override
	public Mono<BootCoin> update(String identityNumber, BootCoin bootcoin) {
		return bootcoinOperations.opsForValue()
				.get(identityNumber)
				.doOnNext(a->bootcoinOperations.opsForValue()
						.set(identityNumber, a))
				.thenReturn(bootcoin.getIdentityNumber())
				.flatMap(bootcoinOperations.opsForValue()::get);
	}

	@Override
	public Mono<Void> delete(String identityNumber) {
		return bootcoinOperations.opsForValue().delete(identityNumber).then();
	}

}
