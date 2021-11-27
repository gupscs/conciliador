package br.silveira.conciliador.organizational.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.organizational.dto.CompanyDto;
import br.silveira.conciliador.organizational.entity.Company;
import br.silveira.conciliador.organizational.mapper.CompanyMapper;
import br.silveira.conciliador.organizational.repository.CompanyRepository;
import br.silveira.conciliador.organizational.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	private static final Logger log = LogManager.getLogger(CompanyServiceImpl.class);
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<CompanyDto> findByEnable(Boolean enable) {
		List<Company> companies = companyRepository.findByEnable(enable);
		return CompanyMapper.mapperCompanyDto(companies);
	}
	
	public void saveCompany(CompanyDto dto) {
		companyRepository.save(CompanyMapper.mapperToEntity(dto));
		log.info(String.format("Company save sucessfully. Company Name: %s - Company Identification No: %s", dto.getName(), dto.getIdentificationNo()));
	}

}
