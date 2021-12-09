package br.silveira.conciliador.costcalc.dto;

import java.util.Date;

import lombok.Data;


@Data
public class ItemAverageCostDto {
	
	private Date startDate;
	
	private String sku;
	
	private Double averageCost;
	
	
}
