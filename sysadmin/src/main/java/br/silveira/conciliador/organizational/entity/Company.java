package br.silveira.conciliador.organizational.entity;

import java.util.Date;
import java.util.List;

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
	
	private String district;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private Long phone1;
	
	private Long phone2;
	
	@Email(message = "Invalid email")
	private String email;
	
	//Taxas do Market Place por pedido
	private List<MktPlaceFee> mktPlaceFees;
	
	//Custos fixos da empresa (aluguel, publicidade, etc)
	private List<FixedCost> fixedCost;
	
	//Impostos por pedido
	private List<Tax> taxes;
	
	//Custo médio dos itens a partir das notas de inbound ou integração com o Bling
	private List<ItemAverageCost> itemAverageCost;
	
	@NotEmpty(message = "insert date required")
	private Date insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;
	
}
