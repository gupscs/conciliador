package br.silveira.conciliador.organizational.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import lombok.Data;

@Data
public class MktPlaceFee {
	
	@NotEmpty(message = "market place required")
	private MktPlaceEnum marketPlace;
	
	private String feeType;
	
	private Double fee;
	
	@NotEmpty(message = "insert date required")
	private LocalDate insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private LocalDate updateDate;
	
	private String updateId;
	
	private Boolean enable;
	
	

}
