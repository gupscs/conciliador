package br.silveira.conciliador.sysadmin.ctr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.sysadmin.dto.UserDto;
import br.silveira.conciliador.sysadmin.service.UserService;

@RestController
public class SysadminController {
	@Autowired
	private UserService userService; 
	
	
	public UserDto findUserByUsername(String username) {
		return userService.findByUsername(username);
	}
	
	

}
