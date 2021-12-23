package br.silveira.conciliador.organizational.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Tax {
	
	private Date taxStart;
	
	private String taxName;
	
	private Double cost;

}
