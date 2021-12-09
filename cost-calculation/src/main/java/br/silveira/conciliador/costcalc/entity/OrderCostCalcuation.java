package br.silveira.conciliador.costcalc.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class OrderCostCalcuation {

	@Id
	private String id;
	
	private String orderId;
	
	private String companyId;
	
	private Calculation mktPlaceFee;
	
	private List<Calculation> itemCost;
	
	private Calculation tax;
	
	private List<Calculation> fixedCost;
	
	private Calculation shippingCost;
	
	private Calculation shippingReturnCost;
		
	private Date insertDate;
	
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	private Boolean enable;
}
