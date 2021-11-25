package br.silveira.conciliador.common.security.jwt;

import lombok.Data;

@Data
public class CurrentUser {
	
	private String token;
	
	private String username;

	public CurrentUser(String token, String username) {
		this.token = token;
		this.username = username;
	}
	
	
	

}
