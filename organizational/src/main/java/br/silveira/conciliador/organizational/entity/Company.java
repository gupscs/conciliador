package br.silveira.conciliador.organizational.entity;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Company {
	
	@Id
	private String id;

	@Indexed(unique=true)
	@NotNull(message = "Compnay identification required")
	private String identificationNo;
	
	@NotNull(message = "Compnay name required")
	private String name;
	
	@NotNull(message = "Compnay short name required")
	private String shortName;
	
	private String address;
	
	private String address2;
	
	private String zipcode;
	
	private String district;
	
	private String city;
	
	private String state;
	
	private String country;
	
	
	private Long phone1;
	
	private Long phone2;
	
	@Email(message = "Invalid email")
	private String email;
	
	private Double taxCost;

	@NotEmpty(message = "insert date required")
	private Date insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;
	
}
