package com.nttdata.bootcamp.bootcoinservice.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.bootcoinservice.application.BootCoinOperations;
import com.nttdata.bootcamp.bootcoinservice.application.repository.BootCoinRepository;
import com.nttdata.bootcamp.bootcoinservice.domain.BootCoin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BootCoinOperationsImpl implements BootCoinOperations {

	@Autowired
	BootCoinRepository repository;

	@Override
	public Flux<BootCoin> queryAll() {
		return repository.getAll();
	}

	@Override
	public Mono<BootCoin> findId(String identityNumber) {
		return repository.getId(identityNumber);
	}

	@Override
	public Mono<BootCoin> create(BootCoin bootcoin) {
		return repository.saveBootCoin(bootcoin);
	}

	@Override
	public Mono<BootCoin> update(String identityNumber, BootCoin bootcoin) {
		return repository.updateBootCoin(identityNumber, bootcoin);
	}

	@Override
	public Mono<Void> delete(String identityNumber) {
		return repository.deleteBootCoin(identityNumber);
	}

}
