package br.silveira.conciliador.organizational.ctr;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import br.silveira.conciliador.organizational.dto.CompanyCostValuesDto;
import br.silveira.conciliador.organizational.dto.CompanyCostValuesRequestDto;
import br.silveira.conciliador.organizational.dto.CompanyDto;


@FeignClient(name = "organizational")
public interface OrganizationalController {
	
	
	@PostMapping("/organizational/saveCompany")
	public ResponseEntity<Void> saveCompany(CompanyDto companyDto);
	
	@PostMapping("/organizational/getCompanyCostValues")
	public ResponseEntity<CompanyCostValuesDto> getCompanyCostValues(CompanyCostValuesRequestDto dto);
}
