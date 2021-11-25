package br.silveira.conciliador.sysadmin.dto;

import lombok.Data;

@Data
public class UserDto {
	
	private String userId;
	private String username;
	private String password;
	private Long companyId;

}
