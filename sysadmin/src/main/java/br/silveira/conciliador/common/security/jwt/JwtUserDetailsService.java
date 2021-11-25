package br.silveira.conciliador.common.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.sysadmin.ctr.SysadminController;
import br.silveira.conciliador.sysadmin.dto.UserDto;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	private SysadminController controller;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto user = controller.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username); 
		} else {
			return JwtUserFactory.create(user);
		}
	}
	
}
