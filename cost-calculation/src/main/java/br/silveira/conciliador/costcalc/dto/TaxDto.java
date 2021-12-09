package br.silveira.conciliador.costcalc.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TaxDto {
	
	private Date taxStart;
	
	private String taxName;
	
	private Double cost;

}
