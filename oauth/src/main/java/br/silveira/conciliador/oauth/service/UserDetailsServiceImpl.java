package br.silveira.conciliador.oauth.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.oauth.dto.UserDetailsDto;
import br.silveira.conciliador.sysadmin.ctr.SysadminController;
import br.silveira.conciliador.sysadmin.dto.UserDto;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger log = LogManager.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private SysadminController sysadminController;

	private ModelMapper mapper = new ModelMapper();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			ResponseEntity<UserDto> response = sysadminController.findUserByUsername(username);
			if(response.getBody() == null) {
				throw new UsernameNotFoundException(String.format("Username: %s not found", username));
			}else {
				return mapper.map(response.getBody(), UserDetailsDto.class);
			}
		} catch (Exception e) {
			log.error("Error to load user "+username, e);
			throw new UsernameNotFoundException(String.format("Username: %s not found, Exception Message: %s", username, e.getMessage()), e);
		}

	}
}
