package br.silveira.conciliador.integrator.dto;

import java.util.Date;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import lombok.Data;

@Data
public class MktPlaceIntegrationConfigDto {
	
	
	private String id;
	
	private String companyId;
	
	private MktPlaceEnum marketPlace;
	
	private String applicationId;
	
	private String clientSecret;
	
	private String authorizationCode;
	
	private String apiToken;	
	
	private String mktPlaceUserId;
	
	private String mktPlaceUsername;
	
	private String apiRefreshToken;
	
	private Date lastApiTokenUpdated;
	
	private Integer expiresIn;
	
	private String redirectUrl;
	
	private Object originalUserData;
	
	private Date insertDate;
	
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;
	
	

}
