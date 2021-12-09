package br.silveira.conciliador.organizational.entity;

import java.util.Date;

import br.silveira.conciliador.common.enums.RatioTypeEnum;
import lombok.Data;

@Data
public class FixedCost {
	
	private Date costStart;
	
	private String costName;
	
	private Double cost;
	
	private RatioTypeEnum ratioType;

}
