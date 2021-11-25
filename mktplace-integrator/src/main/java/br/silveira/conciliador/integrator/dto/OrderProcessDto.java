package br.silveira.conciliador.integrator.dto;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import lombok.Data;

@Data
public class OrderProcessDto {
	
	
	private String queueOrdersId;
	
	private String documentId;
	
	private String applicationId;
	
	private String companyId;
	
	private String apiToken;
	
	private MktPlaceEnum marketPlace;
	
	private String executionId;
	
	

}
