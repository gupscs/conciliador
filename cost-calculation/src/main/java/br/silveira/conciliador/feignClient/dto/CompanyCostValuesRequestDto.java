package br.silveira.conciliador.feignClient.dto;

import java.util.List;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import lombok.Data;

@Data
public class CompanyCostValuesRequestDto {

	private String id;
	
	private MktPlaceEnum marketPlace;
	
	private String feeType;
	
	private List<String> sku;
}
