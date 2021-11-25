package br.silveira.conciliador.integrator.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
@Document
public class QueueNotImplemented  extends QueueCommon{

	@Id
	private String id;	
	
	
	
	
	
	
}
