package br.silveira.conciliador.organizational.dto;

import java.util.List;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class CompanyCostValuesRequestDto{

	private String id;
	
	private MktPlaceEnum marketPlace;
	
	private String feeType;
	
	private List<String> sku;
}
