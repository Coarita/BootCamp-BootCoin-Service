package com.nttdata.bootcamp.bootcoinservice.infrastructure.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.bootcoinservice.application.repository.BootCoinRepository;
import com.nttdata.bootcamp.bootcoinservice.domain.BootCoin;
import com.nttdata.bootcamp.bootcoinservice.infrastructure.model.dao.BootCoinDao;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BootCoinCrudRepository implements BootCoinRepository {

	@Autowired
	IBootCoinCrudRepository repository;
	
	@Override
	public Flux<BootCoin> getAll() {
		return repository.findAll()
				.map(this::mapBootCoinDaoToBootCoin);
	}

	@Override
	public Mono<BootCoin> getId(String identityNumber) {
		return repository.findById(identityNumber)
				.map(this::mapBootCoinDaoToBootCoin);
	}

	@Override
	public Mono<BootCoin> saveBootCoin(BootCoin bootcoin) {
		return repository.save(mapBootCoinToBootCoinDao(bootcoin))
				.map(this::mapBootCoinDaoToBootCoin);
	}

	@Override
	public Mono<BootCoin> updateBootCoin(String identityNumber, BootCoin bootcoin) {
		return Mono.just(bootcoin)
				.doOnNext(e->e.setIdentityNumber(identityNumber))
				.map(this::mapBootCoinToBootCoinDao)
				.flatMap(repository::save)
				.map(this::mapBootCoinDaoToBootCoin);
	}

	@Override
	public Mono<Void> deleteBootCoin(String identityNumber) {
		return repository.deleteById(identityNumber);
	}
	
	private BootCoin mapBootCoinDaoToBootCoin(BootCoinDao bootCoinDao) {
		BootCoin bootCoin = new BootCoin();
		BeanUtils.copyProperties(bootCoinDao, bootCoin);
		return bootCoin;
	}
	
	private BootCoinDao mapBootCoinToBootCoinDao(BootCoin bootCoin) {
		BootCoinDao bootCoinDao = new BootCoinDao();
		BeanUtils.copyProperties(bootCoin, bootCoinDao);
		return bootCoinDao;
	}

}
