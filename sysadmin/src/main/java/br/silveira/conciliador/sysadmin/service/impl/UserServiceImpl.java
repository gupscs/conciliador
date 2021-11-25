package br.silveira.conciliador.sysadmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.silveira.conciliador.sysadmin.dto.UserDto;
import br.silveira.conciliador.sysadmin.entity.User;
import br.silveira.conciliador.sysadmin.repository.UserRepository;
import br.silveira.conciliador.sysadmin.service.UserService;
import br.silveira.conciliador.sysadmin.util.MapperUtil;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return MapperUtil.mapperUserDto(user);
	}

}
