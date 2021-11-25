package br.silveira.conciliador.organizational.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class ItemAverageCost {
	
	//TODO ESTE DOCUMENTO DEVE SER POPULADO PELA INTEGRAÇÃO COM O BLING
	
	@NotNull(message = "start Date required")
	private LocalDate startDate;
	
	@NotNull(message = "sku required")
	private String sku;
	
	@NotNull(message = "average cost required")
	private Double averageCost;
	
	
}
