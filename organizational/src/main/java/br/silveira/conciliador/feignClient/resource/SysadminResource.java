package br.silveira.conciliador.feignClient.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.silveira.conciliador.feignClient.dto.UserDto;

@FeignClient(name = "sysadmin" , path = "/sysadmin")
public interface SysadminResource {

	@PostMapping("/saveUser")
	public  ResponseEntity<String> saveUser(@RequestBody UserDto dto);
	
	@GetMapping("/existUsername/{username}")
	public Boolean existUsername(@PathVariable String username) ;
}
