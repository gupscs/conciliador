package br.silveira.conciliador.sysadmin.ctr;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.silveira.conciliador.sysadmin.dto.UserDto;

@FeignClient(name = "sysadmin" , path = "/sysadmin")
public interface SysadminController {
	
	
	@GetMapping("/findUserByUsername/{username}")
	public ResponseEntity<UserDto> findUserByUsername(@PathVariable String username);

}
