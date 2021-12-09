package br.silveira.conciliador.costcalc.dto;

import br.silveira.conciliador.common.enums.RatioTypeEnum;
import lombok.Data;

@Data
public class FixedCostDto {

	private String costName;

	private Double costRateValue;

	private RatioTypeEnum ratioType;

}
