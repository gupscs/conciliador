package br.silveira.conciliador.sysadmin.service.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.sysadmin.dto.UserDto;
import br.silveira.conciliador.sysadmin.dto.UserFrontDto;
import br.silveira.conciliador.sysadmin.entity.User;
import br.silveira.conciliador.sysadmin.repository.UserRepository;
import br.silveira.conciliador.sysadmin.service.UserService;
import br.silveira.conciliador.sysadmin.util.MapperUtil;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return MapperUtil.mapperUserDto(user);
	}
	
	@Override
	public void saveUser(UserDto dto) throws Exception {
		User user = userRepository.findByUsername(dto.getUsername());
		if(user == null) {
			user = MapperUtil.mapperToEntity(dto);
			user.setInsertDate(new Date());
			user.setBlocked(false);
			user.setEnable(true);
			user.setNeedPasswordChange(true);
			user = userRepository.save(user);
			log.info(String.format("Username %s successufully saved, User ID is %s", dto.getUsername() , user.getId()));
		}else {
			throw new Exception(String.format("Username already exist, check ID: %s | Username: %s", user.getId(), user.getUsername()));
		}
		
	}
	
	@Override
	public void updateUser(UserDto dto) throws Exception {
		Optional<User> user = userRepository.findById(dto.getId());
		if(user.isEmpty()) {
			throw new Exception(String.format("User not exist, check ID: %s | Username: %s", dto.getId(), dto.getUsername()));
		}else {
			user.get().setUpdateDate(new Date());
			user.get().setUpdateId(dto.getUpdateId()==null?dto.getInsertId():dto.getUpdateId());
			user.get().setName(dto.getName());
			user.get().setEmail(dto.getEmail());
			userRepository.save(user.get());
			log.info(String.format("User %s successufully updated", dto.getUsername()));
		}
	}

	@Override
	public UserFrontDto findUserFrontModelByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return MapperUtil.mapperUserFrontDto(user);
	}

}
