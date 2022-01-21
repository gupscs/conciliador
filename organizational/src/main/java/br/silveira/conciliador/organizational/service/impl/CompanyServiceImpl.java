package br.silveira.conciliador.organizational.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import br.silveira.conciliador.common.enums.RoleEnum;
import br.silveira.conciliador.feignClient.dto.UserDto;
import br.silveira.conciliador.feignClient.resource.SysadminResource;
import br.silveira.conciliador.organizational.dto.CompanyCostValuesDto;
import br.silveira.conciliador.organizational.dto.CompanyCostValuesRequestDto;
import br.silveira.conciliador.organizational.dto.CompanyDto;
import br.silveira.conciliador.organizational.dto.RegisterCheckDto;
import br.silveira.conciliador.organizational.dto.RegisterDto;
import br.silveira.conciliador.organizational.entity.Company;
import br.silveira.conciliador.organizational.mapper.CompanyMapper;
import br.silveira.conciliador.organizational.repository.CompanyRepository;
import br.silveira.conciliador.organizational.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static final Logger log = LogManager.getLogger(CompanyServiceImpl.class);

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private SysadminResource sysadminResource;

	@Override
	public List<CompanyDto> findByEnable(Boolean enable) {
		List<Company> companies = companyRepository.findByEnable(enable);
		return CompanyMapper.mapperCompanyDto(companies);
	}

	public void saveCompany(CompanyDto dto) {
		companyRepository.save(CompanyMapper.mapperToEntity(dto));
		log.info(String.format("Company save sucessfully. Company Name: %s - Company Identification No: %s", dto.getName(), dto.getIdentificationNo()));
	}

	@Override
	public CompanyCostValuesDto getCompanyCostValues(CompanyCostValuesRequestDto dto) throws Exception {
		Optional<Company> entity = null;
		switch (getSelectCondition(dto)) {
		case 0:
			entity = companyRepository.findCompanyCostValuesById(dto.getId());
			break;
		case 1:
			entity = companyRepository.findCompanyCostValuesByIdAndMktPlaceAndFeeType(dto.getId(), dto.getMarketPlace(), dto.getFeeType());
			break;
		case 2:
			entity = companyRepository.findCompanyCostValuesByIdAndSku(dto.getId(), dto.getSku());
			break;
		case 3:
			entity = companyRepository.findCompanyCostValuesByIdAndMktPlaceAndFeeTypeAndSku(dto.getId(), dto.getMarketPlace(), dto.getFeeType(), dto.getSku());
			break;
		}
		
		
		return CompanyMapper.mapperToCompanyCostValuesDto(entity.get());
		
	}

	private int getSelectCondition(CompanyCostValuesRequestDto dto) throws Exception {
		if(!StringUtils.hasLength(dto.getFeeType()) && dto.getMarketPlace() == null && CollectionUtils.isEmpty(dto.getSku()) )
		return 0;
		
		if(StringUtils.hasLength(dto.getFeeType()) && dto.getMarketPlace() != null && CollectionUtils.isEmpty(dto.getSku()) )
		return 1;
		
		if(!StringUtils.hasLength(dto.getFeeType()) && dto.getMarketPlace() == null && !CollectionUtils.isEmpty(dto.getSku()) )
		return 2;
		
		if(StringUtils.hasLength(dto.getFeeType()) && dto.getMarketPlace() != null && !CollectionUtils.isEmpty(dto.getSku()) )
		return 3;
		
		throw new Exception("Invlaid Request dto, mandatory fields are missing - DTO: "+dto);
			
		
	}

	@Override
	@Transactional
	public void register(RegisterDto register) throws Exception {
		Company saveCompany = companyRepository.save(CompanyMapper.mapperToEntity(register));		
		UserDto dto = new UserDto();
		dto.setName(register.getUserName());
		dto.setEmail(register.getEmail());
		dto.setUsername(register.getEmail());
		dto.setPassword(register.getPassword());
		dto.setCompanyId(saveCompany.getId());
		dto.setRoles(getRegisterRoles());
		sysadminResource.saveUser(dto);
		log.info("Register Successfully, Company ID: %s"+saveCompany.getId());
	}

	private List<RoleEnum> getRegisterRoles() {
		List<RoleEnum> ret = new ArrayList<RoleEnum>();
		ret.add(RoleEnum.CUSTOMER_ADMIN);
		return ret;
	}

	@Override
	public RegisterCheckDto registerCheck(String identificationNo, String username) {
		Optional<Company> company = companyRepository.findIdByIdentificationNo(identificationNo);
		Boolean existUsername = sysadminResource.existUsername(username);
		RegisterCheckDto dto = new RegisterCheckDto();
		dto.setExistIdentificationNo(company.isPresent());
		dto.setExistUsername(existUsername);
		log.info("Register Check Result: "+dto);
		return dto;
	}

}
