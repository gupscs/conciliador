package br.silveira.conciliador.costcalc.dto;

import java.util.List;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.feignClient.dto.MktPlaceFeeDto;
import lombok.Data;

@Data
public class OrderCalculationValuesDto {
	
	private String orderId;
	
	private String companyId;
	
	private String sellerId;
	
	private MktPlaceEnum mktPlace;
	
	private Double orderAmount;
	
	private Double shippingCost;
	
	private Integer shippingMethodId;

	private String receiverZipcode;

	private List<MktPlaceFeeDto> mktPlaceFees;
	
	private List<FixedCostDto> fixedCost;
	
	private Double taxCost;
	
	private List<ItemCalculationValuesDto> itemCalculationValuesDto;
	
}
