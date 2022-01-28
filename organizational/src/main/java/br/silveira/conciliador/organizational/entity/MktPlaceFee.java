package br.silveira.conciliador.organizational.entity;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import lombok.Data;

@Document
@Data
public class MktPlaceFee {
	
	@Id
	private String id;
	
	private String companyId;
	
	@NotEmpty(message = "market place required")
	private MktPlaceEnum marketPlace;
	
	private String feeType;
	
	private Double fee;
	
	@NotEmpty(message = "insert date required")
	private Date insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;
	
	

}
