package br.silveira.conciliador.orders.entity;

import java.util.List;

import lombok.Data;

@Data
public class OrderCostCalculated {
	
	private String orderCostCalcuationId;
	
	private Double mktPlaceFee;
	
	private Double itemTotalCost;
	
	private Double tax;
	
	private Double fixedTotalCost;
	
	private Double shippingCost;
	
	private Double shippingReturnCost;
	
	private List<ItemCostDetail> itemCostDetail;
	
	private List<FixedCostDetail> fixedCostDetail;
}
