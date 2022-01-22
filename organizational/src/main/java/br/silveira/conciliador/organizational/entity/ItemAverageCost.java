package br.silveira.conciliador.organizational.entity;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
	
public class ItemAverageCost {
	//TODO ESTE DOCUMENTO DEVE SER POPULADO PELA INTEGRAÇÃO COM O BLING
	
	@NotNull(message = "sku required")
	private String sku;
	
	@NotNull(message = "start Date required")
	private Date startDate;
	
	@NotNull(message = "average cost required")
	private Double averageCost;
	
	@NotEmpty(message = "insert date required")
	private Date insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;
	
	
}
