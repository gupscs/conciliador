package br.silveira.conciliador.integrator.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueueCommon {
	
	private String companyId;

	private MktPlaceEnum marketPlace;
	
	private Object notificationOriginalData;
	
	private Object documentOriginalData;
	
	private String applicationId;
	
	private String documentId;
	
	@NotEmpty(message = "process status required")
	private Integer processStatus;
	
	private String processMsg;
	
	@NotEmpty(message = "insert date required")
	private LocalDateTime insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private LocalDateTime updateDate;
	
	private String updateId;

}
