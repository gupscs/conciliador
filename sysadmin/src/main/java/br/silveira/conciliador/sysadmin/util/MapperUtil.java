package br.silveira.conciliador.sysadmin.util;

import org.modelmapper.ModelMapper;

import br.silveira.conciliador.common.enums.RoleEnum;
import br.silveira.conciliador.sysadmin.dto.UserDto;
import br.silveira.conciliador.sysadmin.dto.UserFrontDto;
import br.silveira.conciliador.sysadmin.entity.User;

public class MapperUtil<T,X> {
	
	private static ModelMapper mapper = new ModelMapper();
	

	public static UserDto mapperUserDto(User entity) {
		UserDto dto = entity==null ? null : mapper.map(entity, UserDto.class);
		return dto;
	}


	public static User mapperToEntity(UserDto dto) {
		User user =  mapper.map(dto, User.class);
		user.setRoles(dto.getRoles());
		user.setPassword(PasswordEnconder.convertPassword(dto.getPassword()));
		return user;
	}


	public static UserFrontDto mapperUserFrontDto(User user) {
		UserFrontDto dto = new UserFrontDto();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setCompanyId(user.getCompanyId());
		dto.setEmail(user.getEmail());
		dto.setFullName(user.getName());
		dto.setAvatar("@/assets/images/avatars/13-small.png");
		dto.setRole(getRole(user));
		for (RoleEnum roleEnum : user.getRoles()) {
			dto.addAbility("all",roleEnum.toString());
		}
		return dto;
	}


	private static String getRole(User user) {
		if(user.getRoles() != null && user.getRoles().size() > 0) {
			return user.getRoles().get(0).toString();
		}else {
			return "";
		}
	}
	
	
	

}
