package br.silveira.conciliador.feignClient.dto;

import java.util.List;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.common.enums.OrderStatusEnum;
import lombok.Data;

@Data   
public class OrderValuesDto {
	
	private String id;
	
	private String companyId;
	
	private Double totalAmount;
	
	private MktPlaceEnum mktPlace;
	
	private String feeType;
	
	private String mktPlaceStatus;
	
	private OrderStatusEnum orderStatus;
	
	private List<OrderItemDto> orderItems;

}
