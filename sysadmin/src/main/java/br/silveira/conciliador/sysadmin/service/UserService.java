package br.silveira.conciliador.sysadmin.service;

import br.silveira.conciliador.sysadmin.dto.UserDto;
import br.silveira.conciliador.sysadmin.dto.UserFrontDto;

public interface UserService {
	
	public UserDto findByUsername(String username);

	public void saveUser(UserDto dto) throws Exception;

	public void updateUser(UserDto dto) throws Exception;

	public UserFrontDto findUserFrontModelByUsername(String username);

}
