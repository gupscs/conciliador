package br.silveira.conciliador.organizational.service;

import java.util.List;

import br.silveira.conciliador.organizational.dto.CompanyDto;

public interface CompanyService {
	
	public List<CompanyDto> findByEnable(Boolean enable);
	
	public void saveCompany(CompanyDto dto);

}
