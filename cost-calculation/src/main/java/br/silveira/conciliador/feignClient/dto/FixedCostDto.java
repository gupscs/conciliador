package br.silveira.conciliador.feignClient.dto;

import br.silveira.conciliador.common.enums.RatioTypeEnum;
import lombok.Data;

@Data
public class FixedCostDto {

	private String costName;

	private Double cost;

	private RatioTypeEnum ratioType;

}
