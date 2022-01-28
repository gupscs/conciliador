package br.silveira.conciliador.organizational.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import br.silveira.conciliador.organizational.dto.FixedCostDto;
import br.silveira.conciliador.organizational.dto.RegisterCheckDto;
import br.silveira.conciliador.organizational.dto.RegisterDto;
import br.silveira.conciliador.organizational.entity.Company;
import br.silveira.conciliador.organizational.entity.FixedCost;
import br.silveira.conciliador.organizational.entity.ItemAverageCost;
import br.silveira.conciliador.organizational.entity.MktPlaceFee;
import br.silveira.conciliador.organizational.entity.Tax;
import br.silveira.conciliador.organizational.repository.CompanyRepository;
import br.silveira.conciliador.organizational.repository.FixedCostRepository;
import br.silveira.conciliador.organizational.repository.ItemAverageCostRepository;
import br.silveira.conciliador.organizational.repository.MktPlaceFeeRepository;
import br.silveira.conciliador.organizational.repository.TaxRepository;
import br.silveira.conciliador.organizational.service.CompanyService;
import br.silveira.conciliador.organizational.util.MapperUtil;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static final Logger log = LogManager.getLogger(CompanyServiceImpl.class);

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private FixedCostRepository fixedCostRepository;

	@Autowired
	private ItemAverageCostRepository itemAverageCostRepository;

	@Autowired
	private MktPlaceFeeRepository mktPlaceFeeRepository;

	@Autowired
	private TaxRepository taxRepository;

	@Autowired
	private SysadminResource sysadminResource;

	@Override
	public List<CompanyDto> findByEnable(Boolean enable) {
		List<Company> companies = companyRepository.findByEnable(enable);
		return MapperUtil.mapperCompanyDto(companies);
	}

	@Transactional
	public void saveCompany(CompanyDto dto) {
		companyRepository.save(MapperUtil.mapperToEntity(dto));
		log.info(String.format("Company save sucessfully. Company Name: %s - Company Identification No: %s", dto.getName(), dto.getIdentificationNo()));
	}

	@Override
	public CompanyCostValuesDto getCompanyCostValues(CompanyCostValuesRequestDto dto) throws Exception {
		List<ItemAverageCost> itemAvgCost = null;
		List<MktPlaceFee> mktPlace = null;

		switch (getSelectCondition(dto)) {
		case 0:
			mktPlace = mktPlaceFeeRepository.findByCompanyId(dto.getId());
			itemAvgCost = itemAverageCostRepository.findByCompanyId(dto.getId());
			break;
		case 1:
			mktPlace = mktPlaceFeeRepository.findByMarketPlaceAndFeeType(dto.getMarketPlace(), dto.getFeeType());
			itemAvgCost = itemAverageCostRepository.findByCompanyId(dto.getId());
			break;
		case 2:
			mktPlace = mktPlaceFeeRepository.findByCompanyId(dto.getId());
			itemAvgCost = itemAverageCostRepository.findBySku(dto.getSku());
			break;
		case 3:
			mktPlace = mktPlaceFeeRepository.findByMarketPlaceAndFeeType(dto.getMarketPlace(), dto.getFeeType());
			itemAvgCost = itemAverageCostRepository.findBySku(dto.getSku());
			break;
		}

		List<FixedCost> fixedCost = fixedCostRepository.findByCompanyId(dto.getId());
		List<Tax> tax = taxRepository.findByCompanyId(dto.getId());

		return MapperUtil.mapperToCompanyCostValuesDto(dto, mktPlace, itemAvgCost, fixedCost, tax);

	}

	private int getSelectCondition(CompanyCostValuesRequestDto dto) throws Exception {
		if (!StringUtils.hasLength(dto.getFeeType()) && dto.getMarketPlace() == null && CollectionUtils.isEmpty(dto.getSku()))
			return 0;

		if (StringUtils.hasLength(dto.getFeeType()) && dto.getMarketPlace() != null && CollectionUtils.isEmpty(dto.getSku()))
			return 1;

		if (!StringUtils.hasLength(dto.getFeeType()) && dto.getMarketPlace() == null && !CollectionUtils.isEmpty(dto.getSku()))
			return 2;

		if (StringUtils.hasLength(dto.getFeeType()) && dto.getMarketPlace() != null && !CollectionUtils.isEmpty(dto.getSku()))
			return 3;

		throw new Exception("Invlaid Request dto, mandatory fields are missing - DTO: " + dto);

	}

	@Override
	@Transactional
	public void register(RegisterDto register) throws Exception {
		Company saveCompany = companyRepository.save(MapperUtil.mapperToEntity(register));
		UserDto dto = new UserDto();
		dto.setName(register.getUserName());
		dto.setEmail(register.getEmail());
		dto.setUsername(register.getEmail());
		dto.setPassword(register.getPassword());
		dto.setCompanyId(saveCompany.getId());
		dto.setRoles(getRegisterRoles());
		sysadminResource.saveUser(dto);
		log.info("Register Successfully, Company ID: %s" + saveCompany.getId());
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
		log.info("Register Check Result: " + dto);
		return dto;
	}

	@Override
	@Transactional
	public boolean updateCompanyInfo(CompanyDto companyDto) {
		if (isValidCompanyDtoToUpdate(companyDto)) {
			Optional<Company> entity = companyRepository.findById(companyDto.getId());
			if (entity.isPresent()) {
				Company entityMerged = MapperUtil.mapperEntity(entity.get(), companyDto);
				companyRepository.save(entityMerged);
				log.info("Company Info update successfully: ID: " + companyDto.getId());
				return true;
			} else {
				log.error(String.format("Company ID: %s not found - DTO: %s", companyDto.getId(), companyDto));
			}
		} else {
			log.error("DTO is not valid \nRule[ CompanyDto != null && companyDto.getId() != null && companyDto.getIdentificationNo() != null 	&& companyDto.getName() != null && companyDto.getShortName() != null && companyDto.getUpdateId() != null; \nMissing mandatory Fields in DTO. Pleae check the - DTO: " + companyDto);
		}

		return false;

	}

	private boolean isValidCompanyDtoToUpdate(CompanyDto companyDto) {
		return companyDto != null && companyDto.getId() != null && companyDto.getIdentificationNo() != null && companyDto.getName() != null && companyDto.getShortName() != null && companyDto.getUpdateId() != null;
	}

	@Override
	public FixedCostDto saveFixedCost(FixedCostDto fixedCostDto) {
		
		if(StringUtils.hasText(fixedCostDto.getId())) {
			fixedCostDto.setUpdateDate(new Date());
		}else {
			fixedCostDto.setInsertDate(new Date());
		}
		
		FixedCost entity = MapperUtil.mapperToEntity(fixedCostDto);
		FixedCost saved = fixedCostRepository.save(entity);
		
		fixedCostDto.setId(saved.getId());

		log.info("Fixed Cost saved succussfully - ID: "+fixedCostDto.getId());
		return fixedCostDto;
	}
}
