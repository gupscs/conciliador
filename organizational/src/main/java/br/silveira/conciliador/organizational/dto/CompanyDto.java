package br.silveira.conciliador.organizational.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CompanyDto {
	
	private String id;
	
	private String identificationNo;
	
	private String name;
	
	private String shortName;
	
	private String address;
	
	private String address2;
	
	private String zipcode;
	
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
	
	private Double taxCost;
	
	private Date insertDate;
	
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;

}
