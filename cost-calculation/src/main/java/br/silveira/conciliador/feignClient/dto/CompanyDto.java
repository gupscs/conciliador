package br.silveira.conciliador.feignClient.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CompanyDto {
	
	private Long id;
	
	private String identificationNo;
	
	private String name;
	
	private String shortName;
	
	private String address;
	
	private String address2;
	
	private String district;
	//TODO ALTERAR PARA ENUM
	private String city;
	//TODO ALTERAR PARA ENUM
	private String state;
	//TODO ALTERAR PARA ENUM	
	private String country;
	
	private Long phone1;
	
	private Long phone2;
	
	private String email;
	
	private Date insertDate;
	
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;

}
