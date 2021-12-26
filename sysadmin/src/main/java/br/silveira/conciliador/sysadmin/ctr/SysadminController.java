package br.silveira.conciliador.sysadmin.ctr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.sysadmin.dto.UserDto;
import br.silveira.conciliador.sysadmin.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/sysadmin")
@CrossOrigin(origins = "*")
public class SysadminController {
	
	private static final Logger log = LogManager.getLogger(SysadminController.class);
	
	@Autowired
	private UserService userService; 
	
	@GetMapping("/findUserByUsername/{username}")
	public UserDto findUserByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}
	
	
	@PostMapping("/saveUser")
	public  ResponseEntity<String> saveUser(@RequestBody UserDto dto) {
		try {
			userService.saveUser(dto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Error to save the User", e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

	@PostMapping("/updateUser")
	public  ResponseEntity<String> updateUser(@RequestBody UserDto dto) {
		try {
			userService.updateUser(dto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Error to update the User", e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
