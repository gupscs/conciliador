package br.silveira.conciliador.organizational.dto;

import java.util.Date;

import lombok.Data;


@Data
public class ItemAverageCostDto {
	
	private String id;

	private String companyId;
	
	private Date startDate;
	
	private String sku;
	
	private Double averageCost;
	
	private String insertId;
	
	
}
