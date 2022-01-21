package br.silveira.conciliador.feignClient.dto;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import lombok.Data;

@Data
public class MktPlaceFeeDto {
	
	private MktPlaceEnum marketPlace;
	
	private String feeType;
	
	private Double fee;
	
}
