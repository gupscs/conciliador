package br.silveira.conciliador.sysadmin.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.common.constant.RestTagConstant;
import br.silveira.conciliador.sysadmin.dto.UserDto;
import br.silveira.conciliador.sysadmin.dto.UserFrontDto;
import br.silveira.conciliador.sysadmin.service.UserService;

@RestController
@RequestMapping("/sysadmin")
//@CrossOrigin(origins = "*")
public class SysadminResource {
	
	private static final Logger log = LogManager.getLogger(SysadminResource.class);
	
	@Autowired
	private UserService userService; 
	
	@GetMapping("/findUserByUsername/{username}")
	public ResponseEntity<UserDto> findUserByUsername(@PathVariable String username) {
		try {
			UserDto user = userService.findByUsername(username);
			return ResponseEntity.ok().body(user);
		} catch (Exception e) {
			log.error("Error to save the User", e);
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
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
	

	@GetMapping("/findUserFrontModelByUsername/{username}")
	public ResponseEntity<UserFrontDto> findUserFrontModelByUsername(@PathVariable String username) {
		try {
			UserFrontDto user = userService.findUserFrontModelByUsername(username);
			return ResponseEntity.ok().body(user);
		} catch (Exception e) {
			log.error("Error to save the User", e);
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}
	
	@GetMapping("/existUsername/{username}")
	public ResponseEntity<Boolean> existUsername(@PathVariable String username) {
		try {
			Boolean exsitUser = userService.existUsername(username);
			return ResponseEntity.ok().body(exsitUser);
		} catch (Exception e) {
			log.error("Error to save the User", e);
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

}
