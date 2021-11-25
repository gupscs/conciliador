package br.silveira.conciliador.common.security.jwt;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtAuthenticationRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	
	public JwtAuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	
	
}
