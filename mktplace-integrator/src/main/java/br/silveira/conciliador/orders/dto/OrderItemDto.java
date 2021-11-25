package br.silveira.conciliador.orders.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderItemDto {
	
	private String currency;
	
	@NotNull(message = "sku required")
	private Double totalPrice;
	
	
	@NotNull(message = "sku required")
	private String sku;
	
	private String category;

	private String title;
	
	@NotNull(message = "sku required")
	private Double quantity;
	
	private Double saleFee;
	
	private String typeId;
	
	@NotNull(message = "sku required")
	private Double unitPrice;
	
}
