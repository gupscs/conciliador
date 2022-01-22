package br.silveira.conciliador.integrator.entity;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import lombok.Data;

@Data
@Document
public class MktPlaceIntegrationConfig {
	
	
	@Id
	private String id;
	
	@NotEmpty(message = "companyId required")
	private String companyId;
	
	@NotEmpty(message = "market place required")
	private MktPlaceEnum marketPlace;
	
	private String applicationId;
	
	private String clientSecret;
	
	private String authorizationCode;
	
	private String mktPlaceUserId;
	
	private String mktPlaceUsername;
	
	private String apiToken;
	
	private String apiRefreshToken;
	
	private Date lastApiTokenUpdated;
	
	private Integer expiresIn;
	
	@NotEmpty(message = "insert date required")
	private Date insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;
	
	

}
