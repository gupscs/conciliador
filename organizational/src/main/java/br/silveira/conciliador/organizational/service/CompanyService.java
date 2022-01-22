package br.silveira.conciliador.organizational.service;

import java.util.List;

import br.silveira.conciliador.organizational.dto.CompanyCostValuesDto;
import br.silveira.conciliador.organizational.dto.CompanyCostValuesRequestDto;
import br.silveira.conciliador.organizational.dto.CompanyDto;
import br.silveira.conciliador.organizational.dto.RegisterCheckDto;
import br.silveira.conciliador.organizational.dto.RegisterDto;

public interface CompanyService {
	
	public List<CompanyDto> findByEnable(Boolean enable);
	
	public void saveCompany(CompanyDto dto);

	public CompanyCostValuesDto getCompanyCostValues(CompanyCostValuesRequestDto dto) throws Exception;

	public void register(RegisterDto register) throws Exception;

	public RegisterCheckDto registerCheck(String identificationNo, String username);

	public boolean updateCompanyInfo(CompanyDto companyDto);

}
