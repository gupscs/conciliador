package br.silveira.conciliador.costcalc.dto;

import java.util.Date;

import lombok.Data;


@Data
public class ItemCalculationValuesDto {
	
	private Date startDate;
	
	private String sku;
	
	private String title;
	
	private Double averageCost;
	
	private String currency;
	
	private Double totalPrice;
	
	private String mktPlaceItemId;
	
	private Double quantity;
	
	private Double saleFee;
	
	private String feeTypeId;
	
	private Double unitPrice;
	
	
}
