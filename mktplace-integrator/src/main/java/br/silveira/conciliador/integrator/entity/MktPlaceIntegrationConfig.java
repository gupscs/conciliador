package br.silveira.conciliador.integrator.entity;

import java.time.LocalDateTime;

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
	
	//Mercado Livre: cadastro manual
	private String applicationId;
	
	//Mercado Livre: cadastro manual
	private String clientSecret;
	
	//atualização automática no primeiro login
	private String authorizationCode;
	
	private String mktPlaceUserId;
	
	private String apiToken;
	
	private String apiRefreshToken;
	
	private LocalDateTime lastApiTokenUpdated;
	
	private Integer expiresIn;
	
	@NotEmpty(message = "insert date required")
	private LocalDateTime insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private LocalDateTime updateDate;
	
	private String updateId;
	
	private Boolean enable;
	
	

}
