package br.silveira.conciliador.organizational.entity;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Tax {
	
	private Date taxStart;
	
	private String taxName;
	
	private Double cost;
	
	@NotEmpty(message = "insert date required")
	private Date insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;

}
