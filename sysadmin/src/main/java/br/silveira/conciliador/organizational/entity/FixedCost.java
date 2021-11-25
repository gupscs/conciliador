package br.silveira.conciliador.organizational.entity;

import java.time.LocalDate;

import br.silveira.conciliador.common.enums.RatioType;
import lombok.Data;

@Data
public class FixedCost {
	
	private LocalDate costStart;
	
	private String costName;
	
	private Double cost;
	
	private RatioType ratioType;

}
