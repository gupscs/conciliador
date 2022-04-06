package br.silveira.conciliador.costcalc.dto;

import lombok.Data;

@Data
public class ItemCostDetailDto {
	
	private String sku;

	private String title;
	
	private String calculationDetail;
	
	private Double cost;
	
	private Double mktPlaceFeeCost;
	
	

}
