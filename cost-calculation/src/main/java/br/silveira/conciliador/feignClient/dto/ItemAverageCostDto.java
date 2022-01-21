package br.silveira.conciliador.feignClient.dto;

import java.util.Date;

import lombok.Data;


@Data
public class ItemAverageCostDto {
	
	private Date startDate;
	
	private String sku;
	
	private Double averageCost;
	
	
}
