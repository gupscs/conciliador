package br.silveira.conciliador.sysadmin.service;

import br.silveira.conciliador.sysadmin.dto.UserDto;

public interface UserService {
	
	public UserDto findByUsername(String username);

}
