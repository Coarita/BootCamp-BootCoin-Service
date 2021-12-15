package com.nttdata.bootcamp.bootcoinservice.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class BootCoin implements Serializable {

	private String identityNumber;
	private String identityType;
	private String phoneNumber;
	private String phoneCompany;
	private String email;
	private Integer quantity;

	private static final long serialVersionUID = 1L;

}
