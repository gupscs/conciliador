package br.silveira.conciliador.sysadmin.dto;

import java.util.Date;
import java.util.List;

import br.silveira.conciliador.common.enums.RoleEnum;
import lombok.Data;

@Data
public class UserDto {
	
	private String id;
	
	private String name;
	
	private String username;
	
	private String password;
	
	private String companyId;
	
	private String email;
	
	private Date insertDate;
	
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;
	
	private Boolean blocked;
	
	private Boolean lastPasswordChangeDate;
	
	private Boolean needPasswordChange;
	
	private List<RoleEnum> roles;

}
