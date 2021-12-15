package com.nttdata.bootcamp.bootcoinservice.infrastructure.rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.bootcoinservice.application.BootCoinOperations;
import com.nttdata.bootcamp.bootcoinservice.domain.BootCoin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bootcoins")
public class BootCoinController {
	
	@Autowired
	BootCoinOperations operations;
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Flux<BootCoin>>> get() {
		return Mono.just(ResponseEntity.ok(operations.queryAll()));
	}
	
	@GetMapping(value = "/{identityNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<BootCoin>> getId(@PathVariable String identityNumber) {
		return Mono.just(identityNumber)
				.flatMap(operations::findId)
				.map(ResponseEntity::ok);
	}
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<BootCoin>> post(@RequestBody BootCoin bootCoin) {
		return Mono.just(bootCoin)
				.flatMap(operations::create)
				.map(ResponseEntity::ok);
	}
	
	@PutMapping(value = "/{identityNumber}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<BootCoin>> put(@PathVariable String identityNumber, @RequestBody BootCoin bootCoin){
		return operations.findId(identityNumber)
				.flatMap(a->operations.update(identityNumber, bootCoin))
				.map(this::postResponse);
	}
	
	@DeleteMapping(value = "/{identityNumber}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String identityNumber) {
		return Mono.just(identityNumber)
				.flatMap(operations::delete)
				.map(ResponseEntity::ok);
	}
	
	private ResponseEntity<BootCoin> postResponse(BootCoin bootCoin){
		return ResponseEntity.created(URI.create("/bootcoins/" + bootCoin.getIdentityNumber())).body(bootCoin);
	}

}
