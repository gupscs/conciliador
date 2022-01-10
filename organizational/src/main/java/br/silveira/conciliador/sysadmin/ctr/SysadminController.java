package br.silveira.conciliador.sysadmin.ctr;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import br.silveira.conciliador.sysadmin.dto.UserDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "sysadmin" , path = "/sysadmin")
public interface SysadminController {

	@PostMapping("/saveUser")
	public  ResponseEntity<String> saveUser(@RequestBody UserDto dto);
}
