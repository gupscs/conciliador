package br.silveira.conciliador.costcalc.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderCalculationValuesDto {
	
	private String orderId;
	
	private String companyId;
	
	private Double orderAmount;
	
	private String feeType;
	
	private Double feeAmount;
	
	private List<FixedCostDto> fixedCost;
	
	private Double taxCost;
	
	private List<ItemAverageCostDto> itemAverageCost;
	
}
