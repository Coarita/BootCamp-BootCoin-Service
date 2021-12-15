package com.nttdata.bootcamp.bootcoinservice.infrastructure.model.dao;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("bootcoin")
public class BootCoinDao implements Serializable {

	@Id
	private String identityNumber;
	private String identityType;
	private String phoneNumber;
	private String phoneCompany;
	private String email;
	private Integer quantity;

	private static final long serialVersionUID = 1L;
}
