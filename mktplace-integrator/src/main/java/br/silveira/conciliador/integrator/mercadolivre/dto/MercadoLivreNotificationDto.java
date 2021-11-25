package br.silveira.conciliador.integrator.mercadolivre.dto;

import lombok.Data;

@Data
public class MercadoLivreNotificationDto {
	
	
	 private String resource;
	 private Long user_id;
	 private String topic;
	 private Long application_id;
	 private Integer attempts;
	 private String sent;
	 private String received;

}
