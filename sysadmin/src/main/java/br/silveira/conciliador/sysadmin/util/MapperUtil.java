package br.silveira.conciliador.sysadmin.util;

import org.modelmapper.ModelMapper;

import br.silveira.conciliador.sysadmin.dto.UserDto;
import br.silveira.conciliador.sysadmin.entity.User;

public class MapperUtil<T,X> {
	
	private static ModelMapper mapper = new ModelMapper();
	

	public static UserDto mapperUserDto(User entity) {
		UserDto dto = mapper.map(entity, UserDto.class);
		return dto;
	}


	public static User mapperToEntity(UserDto dto) {
		User user =  mapper.map(dto, User.class);
		user.setRoles(dto.getRoles());
		return user;
	}
	
	
	

}
