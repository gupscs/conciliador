package br.silveira.conciliador.orders.dto;

import lombok.Data;

@Data
public class ItemCostDetailDto {
	
	private String sku;

	private String title;
	
	private Double cost;
	
	private Double mktPlaceFeeCost;

}
