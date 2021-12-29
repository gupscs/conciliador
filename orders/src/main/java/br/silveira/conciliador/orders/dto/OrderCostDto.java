package br.silveira.conciliador.orders.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderCostDto {
	
	private String id;
	
	private Double mktPlaceFee;
	
	private Double itemTotalCost;
	
	private Double tax;
	
	private Double fixedTotalCost;
	
	private Double shippingCost;
	
	private Double shippingReturnCost;
	
	private List<ItemCostDetailDto> itemCostDetail;
	
	private List<FixedCostDetailDto> fixedCostDetail;
	
	private String userId;
}
