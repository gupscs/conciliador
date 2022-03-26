package br.silveira.conciliador.orders.service;

import br.silveira.conciliador.orders.dto.OrderCostCalculatedDto;
import br.silveira.conciliador.orders.dto.OrderDto;
import br.silveira.conciliador.orders.dto.OrderValuesDto;

public interface OrderService {
	
	
	public void saveOrder(OrderDto orderDto);

	public OrderValuesDto getOrderValues(String orderId) throws Exception;

	public void saveOrderCost(OrderCostCalculatedDto orderCostCalculatedDto) throws Exception;

}
