package br.silveira.conciliador.organizational.dto;

import java.util.List;

import lombok.Data;

@Data
public class CompanyCostValuesDto {
	
	private String id;

	private String identificationNo;
	
	private String shortName;
	
	//Taxas do Market Place por pedido
	private List<MktPlaceFeeDto> mktPlaceFees;
	
	private List<FixedCostDto> fixedCost;
	
	private TaxDto tax;
	
	private List<ItemAverageCostDto> itemAverageCost;

	
	

}
