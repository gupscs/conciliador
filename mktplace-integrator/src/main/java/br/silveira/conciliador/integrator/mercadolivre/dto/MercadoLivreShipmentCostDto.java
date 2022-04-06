package br.silveira.conciliador.integrator.mercadolivre.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MercadoLivreShipmentCostDto {
	
	private String companyId;
	
	private Long mktPlaceUserId;
	
	private String itemId;
	
	private Double quantity;
	
	private Double shipmentCost;	
	
}
