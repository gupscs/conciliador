package br.silveira.conciliador.feignClient.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.silveira.conciliador.feignClient.dto.CompanyCostValuesDto;
import br.silveira.conciliador.feignClient.dto.CompanyCostValuesRequestDto;
import br.silveira.conciliador.feignClient.dto.CompanyDto;


@FeignClient(name = "organizational")
public interface OrganizationalResource {
	
	
	@PostMapping("/organizational/saveCompany")
	public void saveCompany(@RequestBody CompanyDto companyDto);
	
	@PostMapping("/organizational/getCompanyCostValues")
	public CompanyCostValuesDto getCompanyCostValues(@RequestBody CompanyCostValuesRequestDto dto);
}
