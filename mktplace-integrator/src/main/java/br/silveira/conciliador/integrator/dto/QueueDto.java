package br.silveira.conciliador.integrator.dto;

import java.util.Date;

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
	
	private Date insertDate;
	
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;

}
