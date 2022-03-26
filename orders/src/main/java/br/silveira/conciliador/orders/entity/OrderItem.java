package br.silveira.conciliador.orders.entity;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderItem {
	
	private String currency;
	
	@NotNull(message = "sku required")
	private Double totalPrice;
	
	@NotNull(message = "sku required")
	private String sku;
	
	private String mktPlaceItemId;
	
	private String category;

	private String title;
	
	@NotNull(message = "sku required")
	private Double quantity;
	
	private Double saleFee;
	
	private String feeTypeId;
	
	@NotNull(message = "sku required")
	private Double unitPrice;
	
}
