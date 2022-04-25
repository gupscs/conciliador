package br.silveira.conciliador.orders.entity;

import lombok.Data;

@Data
public class ItemCostDetail {
	
	private String sku;
	
	private String mktPlaceItemId;

	private String title;
	
	private Double cost;
	
	private Double mktPlaceFeeCost;

}
