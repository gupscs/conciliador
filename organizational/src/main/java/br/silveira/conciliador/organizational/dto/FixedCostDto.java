package br.silveira.conciliador.organizational.dto;

import br.silveira.conciliador.common.enums.RatioTypeEnum;
import lombok.Data;

@Data
public class FixedCostDto {
	
	private String companyId;

	private String costName;

	private Double costRateValue;

	private RatioTypeEnum ratioType;
	
	private String insertId;

}
