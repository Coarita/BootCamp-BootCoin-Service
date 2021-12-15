package com.nttdata.bootcamp.bootcoinservice.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.nttdata.bootcamp.bootcoinservice.infrastructure.model.dao.BootCoinDao;

public interface IBootCoinCrudRepository extends ReactiveCrudRepository<BootCoinDao, String> {

}
