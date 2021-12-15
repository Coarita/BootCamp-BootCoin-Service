package com.nttdata.bootcamp.bootcoinservice.application.repository;

import com.nttdata.bootcamp.bootcoinservice.domain.BootCoin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootCoinRepository {

	Flux<BootCoin> getAll();

	Mono<BootCoin> getId(String identityNumber);

	Mono<BootCoin> saveBootCoin(BootCoin bootcoin);

	Mono<BootCoin> updateBootCoin(String identityNumber, BootCoin bootcoin);

	Mono<Void> deleteBootCoin(String identityNumber);

}
