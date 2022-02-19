package br.silveira.conciliador.apigateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AliveResource {
	
	@GetMapping("/")
	public  ResponseEntity<String> alive(){
		return ResponseEntity.ok("alive");
	}

}
