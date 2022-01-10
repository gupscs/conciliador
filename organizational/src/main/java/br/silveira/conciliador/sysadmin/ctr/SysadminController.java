package br.silveira.conciliador.sysadmin.ctr;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.silveira.conciliador.sysadmin.dto.UserDto;

@FeignClient(name = "sysadmin" , path = "/sysadmin")
public interface SysadminController {

	@PostMapping("/saveUser")
	public  ResponseEntity<String> saveUser(@RequestBody UserDto dto);
}
