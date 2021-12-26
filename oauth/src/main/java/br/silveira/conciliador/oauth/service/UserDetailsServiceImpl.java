package br.silveira.conciliador.oauth.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.common.constant.RestTagConstant;
import br.silveira.conciliador.oauth.dto.UserDetailsDto;
import br.silveira.conciliador.sysadmin.ctr.SysadminController;
import br.silveira.conciliador.sysadmin.dto.UserDto;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private SysadminController sysadminController;
	
	private ModelMapper mapper = new ModelMapper();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ResponseEntity<UserDto> response = sysadminController.findUserByUsername(username);
		checkResponse(username, response);
		return mapper.map(response.getBody(), UserDetailsDto.class);
		
	}

	private void checkResponse(String username, ResponseEntity<UserDto> response) throws UsernameNotFoundException  {
		if(response.getStatusCode().isError()) {
			throw new UsernameNotFoundException(String.format("Username: %s not found because an error in sysadmin services - Error: %s", username, response.getHeaders().getFirst(RestTagConstant.HD_ERROR_MSG_TAG)));
		}else if(response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			throw new UsernameNotFoundException(String.format("Username: %s not found", username));	
		}
	}

}
