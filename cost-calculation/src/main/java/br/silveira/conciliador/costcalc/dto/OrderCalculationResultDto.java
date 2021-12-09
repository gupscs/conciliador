package br.silveira.conciliador.costcalc.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderCalculationResultDto {
	
	public OrderCalculationResultDto() {}
	
	public OrderCalculationResultDto(String orderId, String companyId, Double orderAmount) {
		this.orderId = orderId;
		this.companyId = companyId;
		this.orderAmount = orderAmount;
	}

	public OrderCalculationResultDto(String orderId, String companyId, Double orderAmount, CalculationDto mktPlaceFee, Double totalItemCost , List<ItemCostDetailDto> itemCostDetail, CalculationDto tax, Double totalFixedCost, List<CalculationDto> fixedCost, CalculationDto shippingCost, Double netAmount) {
		this.orderId = orderId;
		this.companyId = companyId;
		this.orderAmount = orderAmount;
		this.mktPlaceFee = mktPlaceFee;
		this.totalItemCost = totalItemCost;
		this.itemCostDetail = itemCostDetail;
		this.tax = tax;
		this.totalFixedCost = totalFixedCost;
		this.fixedCost = fixedCost;
		this.shippingCost = shippingCost;
		this.netAmount = netAmount;
	}

	private String orderId;
	
	private String companyId;
	
	private Double orderAmount;
	
	private CalculationDto mktPlaceFee;
	
	private List<ItemCostDetailDto> itemCostDetail;
	
	private Double totalItemCost;
	
	private CalculationDto tax;
	
	private List<CalculationDto> fixedCost;
	
	private Double totalFixedCost;
	
	private CalculationDto shippingCost;
	
	private Double netAmount;
	


}
