package br.silveira.conciliador.organizational.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.silveira.conciliador.organizational.dto.CompanyDto;
import br.silveira.conciliador.organizational.entity.Company;

public class MapperUtil {
	
	private static ModelMapper mapper = new ModelMapper();
	
	
	public static List<CompanyDto> mapperCompanyDto(List<Company> entities) {
		return entities.stream().map(entity -> mapperCompanyDto(entity)).collect(Collectors.toList());
	}

	public static CompanyDto mapperCompanyDto(Company entity) {
		CompanyDto dto = mapper.map(entity, CompanyDto.class);
		return dto;
	}

}
