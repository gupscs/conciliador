package br.silveira.conciliador.feignClient.dto;

import java.util.Date;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import lombok.Data;

@Data
public class CompanyMktPlaceDto {
	
	private String id;

	private Long companyId;
	
	private MktPlaceEnum marketPlace;
	
	private String apiToken;
	
	private String webUser;
	
	private String webPwd;
	
	private Date insertDate;
	
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;
	

}
