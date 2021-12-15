package com.nttdata.bootcamp.bootcoinservice.application;

import com.nttdata.bootcamp.bootcoinservice.domain.BootCoin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootCoinOperations {

	Flux<BootCoin> queryAll();

	Mono<BootCoin> findId(String identityNumber);

	Mono<BootCoin> create(BootCoin bootcoin);

	Mono<BootCoin> update(String identityNumber, BootCoin bootcoin);

	Mono<Void> delete(String identityNumber);
	
	// Mono<StatementDto> send(StatementDto dto);

}
