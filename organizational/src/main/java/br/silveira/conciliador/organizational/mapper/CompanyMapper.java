package br.silveira.conciliador.organizational.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import br.silveira.conciliador.organizational.dto.CompanyCostValuesDto;
import br.silveira.conciliador.organizational.dto.CompanyDto;
import br.silveira.conciliador.organizational.entity.Company;

public class CompanyMapper {
	
	private static ModelMapper mapper = new ModelMapper();
	
	
	public static List<CompanyDto> mapperCompanyDto(List<Company> entities) {
		return entities.stream().map(entity -> mapperCompanyDto(entity)).collect(Collectors.toList());
	}

	public static CompanyDto mapperCompanyDto(Company entity) {
		return mapper.map(entity, CompanyDto.class);
	}

	public static Company mapperToEntity(CompanyDto dto) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(dto, Company.class);
	}

	public static CompanyCostValuesDto mapperToCompanyCostValuesDto(Company entity) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(entity, CompanyCostValuesDto.class);
	}

}