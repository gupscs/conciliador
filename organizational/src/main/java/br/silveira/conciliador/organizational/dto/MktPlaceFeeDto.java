package br.silveira.conciliador.organizational.dto;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import lombok.Data;

@Data
public class MktPlaceFeeDto {

	private String id;

	private String companyId;

	private MktPlaceEnum marketPlace;

	private String feeType;

	private Double fee;

}
