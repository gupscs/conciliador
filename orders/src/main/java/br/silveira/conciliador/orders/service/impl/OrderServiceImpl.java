package br.silveira.conciliador.orders.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.orders.dto.OrderDto;
import br.silveira.conciliador.orders.entity.Order;
import br.silveira.conciliador.orders.mapper.OrderDtoMapper;
import br.silveira.conciliador.orders.repository.OrderRepository;
import br.silveira.conciliador.orders.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void saveOrder(OrderDto orderDto) {
		Order entity = OrderDtoMapper.mapperToOrderEntity(orderDto);
		orderRepository.save(entity);
	}
	
	

}
