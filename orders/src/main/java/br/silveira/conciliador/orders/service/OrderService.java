package br.silveira.conciliador.orders.service;

import br.silveira.conciliador.orders.dto.OrderCostDto;
import br.silveira.conciliador.orders.dto.OrderDto;
import br.silveira.conciliador.orders.dto.OrderValuesDto;

public interface OrderService {
	
	
	public void saveOrder(OrderDto orderDto);

	public OrderValuesDto getOrderValues(String orderId);

	public void saveOrderCost(OrderCostDto orderCostDto);

}
