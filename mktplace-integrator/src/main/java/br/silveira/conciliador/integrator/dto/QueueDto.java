package br.silveira.conciliador.integrator.dto;

import java.time.LocalDateTime;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import lombok.Data;

@Data
public class QueueDto {
	
	private String id;

	private String companyId;

	private MktPlaceEnum marketPlace;
	
	private Object notificationOriginalData;
	
	private Object documentOriginalData;
	
	private String mktPlaceUserId;
	
	private String applicationId;
	
	private String documentId;
	
	private Integer processStatus;
	
	private String processMsg;
	
	private LocalDateTime insertDate;
	
	private String insertId;
	
	private LocalDateTime updateDate;
	
	private String updateId;

}
