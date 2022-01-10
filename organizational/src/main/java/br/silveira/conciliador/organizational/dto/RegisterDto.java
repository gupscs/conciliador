package br.silveira.conciliador.organizational.dto;

import lombok.Data;

@Data
public class RegisterDto {

	private String identificationNo;

	private String name;

    private Long phone1;
	
	private String email;
	
	private String userName;
	
	private String password;
	
	
}
