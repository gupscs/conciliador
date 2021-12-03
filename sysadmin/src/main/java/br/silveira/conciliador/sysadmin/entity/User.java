package br.silveira.conciliador.sysadmin.entity;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class User {
	
	@Id
	private Long id;
	
	@NotEmpty(message = "name required")
	private String name;
	
	@NotEmpty(message = "username required")
	@Indexed(unique = true)
	private String username;
	
	@NotEmpty(message = "password required")
	private String password;
	
	@Indexed
	@NotEmpty(message = "company id required")
	private Long companyId;
	
	@Email(message = "Invalid email")
	@Indexed(unique = true)
	private String email;
	
	@NotEmpty(message = "insert date required")
	private Date insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;
	
	private Boolean blocked;

	
}
