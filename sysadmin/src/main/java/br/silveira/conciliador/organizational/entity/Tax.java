package br.silveira.conciliador.organizational.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Tax {
	
	private LocalDate taxStart;
	
	private String taxName;
	
	private Double cost;

}
