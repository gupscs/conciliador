package br.silveira.conciliador.organizational.ctr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.common.dto.RestResponseDto;
import br.silveira.conciliador.organizational.dto.CompanyCostValuesDto;
import br.silveira.conciliador.organizational.dto.CompanyCostValuesRequestDto;
import br.silveira.conciliador.organizational.dto.CompanyDto;
import br.silveira.conciliador.organizational.service.CompanyService;

@RestController
@RequestMapping("/api/organizational")
@CrossOrigin(origins = "*")
public class OrganizationalController {
	
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/saveCompany")
	public ResponseEntity<RestResponseDto<?>> saveCompany(CompanyDto companyDto){
		try {
			companyService.saveCompany(companyDto);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(new RestResponseDto<>(e.getMessage()));
		}
	}
	
	@PostMapping("/getCompanyCostValues")
	public ResponseEntity<RestResponseDto<CompanyCostValuesDto>> getCompanyCostValues(CompanyCostValuesRequestDto dto){
		try {
			CompanyCostValuesDto ret = companyService.getCompanyCostValues(dto);
			return ResponseEntity.ok(new RestResponseDto<CompanyCostValuesDto>(ret));
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(new RestResponseDto<>(e.getMessage()));
		}
	}

}
