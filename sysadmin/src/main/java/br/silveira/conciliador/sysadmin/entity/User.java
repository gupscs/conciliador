  package br.silveira.conciliador.sysadmin.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import br.silveira.conciliador.common.enums.RoleEnum;
import lombok.Data;

@Document
@Data
public class User {
	
	@Id
	private String id;
	
	@NotEmpty(message = "name required")
	private String name;
	
	@NotEmpty(message = "username required")
	@Indexed(unique = true)
	private String username;
	
	@NotEmpty(message = "password required")
	private String password;
	
	@Indexed
	@NotEmpty(message = "company id required")
	private String companyId;
	
	@Email(message = "Invalid email")
	@Indexed(unique = true)
	private String email;
	
	private String avatar;
	
	@NotEmpty(message = "insert date required")
	private Date insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;
	
	private Boolean blocked;
	
	private Boolean lastPasswordChangeDate;
	
	private Boolean needPasswordChange;
	
	private List<RoleEnum> roles;

	
}
