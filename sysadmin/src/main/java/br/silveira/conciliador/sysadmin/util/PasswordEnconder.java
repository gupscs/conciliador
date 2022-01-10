package br.silveira.conciliador.sysadmin.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEnconder {
	
	private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	public static String convertPassword(String rawPassword) {
		return bCryptPasswordEncoder.encode(rawPassword).toString();
	}

}
