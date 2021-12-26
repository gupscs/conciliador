package br.silveira.conciliador.organizational.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TaxDto {
	
	private Date taxStart;
	
	private String taxName;
	
	private Double cost;

}