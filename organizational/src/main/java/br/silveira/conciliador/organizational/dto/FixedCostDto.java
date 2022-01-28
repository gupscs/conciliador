package br.silveira.conciliador.organizational.dto;

import java.util.Date;

import br.silveira.conciliador.common.enums.RatioTypeEnum;
import lombok.Data;

@Data
public class FixedCostDto {
	
	private String id;

	private String companyId;

	private String costName;

	private Double costRateValue;

	private RatioTypeEnum ratioType;
	
	private Date insertDate;
	
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;


}
