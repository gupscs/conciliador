package br.silveira.conciliador.feignClient.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.silveira.conciliador.feignClient.dto.CompanyCostValuesDto;
import br.silveira.conciliador.feignClient.dto.CompanyCostValuesRequestDto;
import br.silveira.conciliador.feignClient.dto.CompanyDto;


@FeignClient(name = "organizational")
public interface OrganizationalResource {
	
	
	@PostMapping("/organizational/saveCompany")
	public ResponseEntity<Void> saveCompany(CompanyDto companyDto);
	
	@GetMapping("/organizational/getCompanyCostValues")
	public ResponseEntity<CompanyCostValuesDto> getCompanyCostValues(CompanyCostValuesRequestDto dto);
}
