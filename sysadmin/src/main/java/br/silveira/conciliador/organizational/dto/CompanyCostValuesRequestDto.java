package br.silveira.conciliador.organizational.dto;

import java.util.List;

import br.silveira.conciliador.common.dto.RestResponseCommonDto;
import br.silveira.conciliador.common.enums.MktPlaceEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class CompanyCostValuesRequestDto extends RestResponseCommonDto{

	private String id;
	
	private MktPlaceEnum marketPlace;
	
	private String feeType;
	
	private List<String> sku;
}
