package br.silveira.conciliador.organizational.util;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.util.CollectionUtils;

import br.silveira.conciliador.organizational.dto.CompanyCostValuesDto;
import br.silveira.conciliador.organizational.dto.CompanyCostValuesRequestDto;
import br.silveira.conciliador.organizational.dto.CompanyDto;
import br.silveira.conciliador.organizational.dto.FixedCostDto;
import br.silveira.conciliador.organizational.dto.ItemAverageCostDto;
import br.silveira.conciliador.organizational.dto.MktPlaceFeeDto;
import br.silveira.conciliador.organizational.dto.RegisterDto;
import br.silveira.conciliador.organizational.dto.TaxDto;
import br.silveira.conciliador.organizational.entity.Company;
import br.silveira.conciliador.organizational.entity.FixedCost;
import br.silveira.conciliador.organizational.entity.ItemAverageCost;
import br.silveira.conciliador.organizational.entity.MktPlaceFee;
import br.silveira.conciliador.organizational.entity.Tax;

public class MapperUtil {
	
	private static ModelMapper mapper = new ModelMapper();
	
	
	public static List<CompanyDto> mapperCompanyDto(List<Company> entities) {
		return entities.stream().map(entity -> mapperCompanyDto(entity)).collect(Collectors.toList());
	}

	public static CompanyDto mapperCompanyDto(Company entity) {
		CompanyDto dto = mapper.map(entity, CompanyDto.class);
		return dto;
	}

	public static Company mapperToEntity(CompanyDto dto) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(dto, Company.class);
	}

	public static CompanyCostValuesDto mapperToCompanyCostValuesDto(Company entity) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(entity, CompanyCostValuesDto.class);
	}

	public static CompanyDto mapperToCompanyDto(RegisterDto register) {
		return mapper.map(register, CompanyDto.class);
	}

	public static Company mapperToEntity(RegisterDto register) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Company ret = mapper.map(register, Company.class);
		ret.setInsertDate(new Date());
		ret.setInsertId(register.getEmail());
		return ret;
	}

	public static Company mapperEntity(Company entity, CompanyDto companyDto) {
		entity.setName(companyDto.getName());
		entity.setShortName(companyDto.getShortName());
		entity.setAddress(companyDto.getAddress());
		entity.setAddress2(companyDto.getAddress2());
		entity.setZipcode(companyDto.getZipcode());
		entity.setDistrict(companyDto.getDistrict());
		entity.setCity(companyDto.getCity() != null ? companyDto.getCity().toUpperCase() : null);
		entity.setState(companyDto.getState() != null ? companyDto.getState().toUpperCase() : null);
		entity.setCountry(companyDto.getCountry());
		entity.setPhone1(companyDto.getPhone1());
		entity.setPhone2(companyDto.getPhone2());
		entity.setEmail(companyDto.getEmail());
		entity.setUpdateDate(new Date());
		entity.setUpdateId(companyDto.getUpdateId());
		return entity;
	}

	public static FixedCost mapperToEntity(FixedCostDto fixedCostDto) {
		return mapper.map(fixedCostDto, FixedCost.class);
	}

	public static CompanyCostValuesDto mapperToCompanyCostValuesDto(CompanyCostValuesRequestDto dto, List<MktPlaceFee> mktPlace, List<ItemAverageCost> itemAvgCost, List<FixedCost> fixedCost, List<Tax> tax) {
		List<MktPlaceFeeDto> mktPlaceDto = mktPlace.stream().map(t -> mapper.map(t, MktPlaceFeeDto.class)).collect(Collectors.toList());
		List<ItemAverageCostDto> itemAverageCostDto = itemAvgCost.stream().map(t -> mapper.map(t, ItemAverageCostDto.class)).collect(Collectors.toList());
		List<FixedCostDto> fixedCostDto = fixedCost.stream().map(t -> mapper.map(t, FixedCostDto.class)).collect(Collectors.toList());
		List<TaxDto> taxDto = tax.stream().map(t -> mapper.map(t, TaxDto.class)).collect(Collectors.toList());
		CompanyCostValuesDto ret = new CompanyCostValuesDto();
		ret.setFixedCost(fixedCostDto);
		ret.setItemAverageCost(itemAverageCostDto);
		ret.setMktPlaceFees(mktPlaceDto);
		ret.setTax(CollectionUtils.isEmpty(taxDto)?null:taxDto.get(0));
		ret.setId(dto.getId());
		return ret;
	}

	public static ItemAverageCost mapperToEntity(ItemAverageCostDto itemAverageCostDto) {
		return mapper.map(itemAverageCostDto, ItemAverageCost.class);
	}	

}
