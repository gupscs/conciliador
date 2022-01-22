package br.silveira.conciliador.organizational.entity;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import br.silveira.conciliador.common.enums.RatioTypeEnum;
import lombok.Data;

@Data
public class FixedCost {
	
	private Date costStart;
	
	private String costName;
	
	private Double cost;
	
	private RatioTypeEnum ratioType;
	
	@NotEmpty(message = "insert date required")
	private Date insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;

}
