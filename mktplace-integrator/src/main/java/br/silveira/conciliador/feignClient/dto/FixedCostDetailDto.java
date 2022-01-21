package br.silveira.conciliador.feignClient.dto;

import lombok.Data;

@Data
public class FixedCostDetailDto {
	
	private String costName;
	
	private Double cost;

}
