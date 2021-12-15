package br.silveira.conciliador.orders.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LocationDto {
	
	@NotNull(message = "ID is mandatory")
	private String id;
	
	@NotNull(message = "Name is mandatory")
	private String name;
	
	private String nickname;
	
	private String phoneNo;
	
	private String altPhoneNo;
	
	@Email(message = "invalid email")
	private String email;
	

}
