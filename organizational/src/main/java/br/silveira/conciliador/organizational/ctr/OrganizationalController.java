package br.silveira.conciliador.organizational.ctr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.common.constant.RestTagConstant;
import br.silveira.conciliador.organizational.dto.CompanyCostValuesDto;
import br.silveira.conciliador.organizational.dto.CompanyCostValuesRequestDto;
import br.silveira.conciliador.organizational.dto.CompanyDto;
import br.silveira.conciliador.organizational.dto.RegisterDto;
import br.silveira.conciliador.organizational.service.CompanyService;

@RestController
@RequestMapping("/organizational")
//@CrossOrigin(origins = "*")
public class OrganizationalController {

	@Autowired
	private CompanyService companyService;

	@PostMapping("/saveCompany")
	public ResponseEntity<Void> saveCompany(CompanyDto companyDto) {
		try {
			companyService.saveCompany(companyDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@GetMapping("/getCompanyCostValues")
	public ResponseEntity<CompanyCostValuesDto> getCompanyCostValues(CompanyCostValuesRequestDto dto) {
		try {
			CompanyCostValuesDto ret = companyService.getCompanyCostValues(dto);
			if (ret == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok(ret);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody RegisterDto register) {
		try {
			companyService.register(register);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

}
