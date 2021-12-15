package br.silveira.conciliador.organizational.ctr;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import br.silveira.conciliador.common.dto.RestResponseDto;
import br.silveira.conciliador.organizational.dto.CompanyCostValuesDto;
import br.silveira.conciliador.organizational.dto.CompanyCostValuesRequestDto;
import br.silveira.conciliador.organizational.dto.CompanyDto;


@FeignClient(name = "organizationalController", url = "${sysadmin.url}")
public interface OrganizationalController {
	
	
	@PostMapping("/saveCompany")
	public ResponseEntity<RestResponseDto<?>> saveCompany(CompanyDto companyDto);
	
	@PostMapping("/getCompanyCostValues")
	public RestResponseDto<CompanyCostValuesDto> getCompanyCostValues(CompanyCostValuesRequestDto dto);
}
