package br.silveira.conciliador.orders.service.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.orders.dto.OrderCostDto;
import br.silveira.conciliador.orders.dto.OrderDto;
import br.silveira.conciliador.orders.dto.OrderValuesDto;
import br.silveira.conciliador.orders.entity.Order;
import br.silveira.conciliador.orders.mapper.OrderMapper;
import br.silveira.conciliador.orders.repository.OrderRepository;
import br.silveira.conciliador.orders.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void saveOrder(OrderDto orderDto) {
		Order entity = OrderMapper.mapperToOrderEntity(orderDto);
		Optional<Order> entityId = orderRepository.findIdByCompanyIdAndMktPlaceAndOrderId(orderDto.getCompanyId(), orderDto.getMktPlace(), orderDto.getOrderId());
		if(entityId.isPresent()) {
			entity.setInsertDate(entityId.get().getInsertDate());
			entity.setInsertId(entityId.get().getInsertId());
			entity.setUpdateDate(new Date());
			entity.setUpdateId(orderDto.getInsertId());
			entity.setId(entityId.get().getId());
		}
		orderRepository.save(entity);
		log.info(String.format("Order Id: %s saved successfully", orderDto.getOrderId()));
	}

	@Override
	public OrderValuesDto getOrderValues(String orderId) {
		Optional<Order> order = orderRepository.getValuesById(orderId);
		if(order.isPresent()) {
			OrderValuesDto dto = OrderMapper.mapperToOrderValuesDto(order.get());
		}
		
		return null;
	}

	@Override
	public void saveOrderCost(OrderCostDto orderCostDto) {
		// TODO Auto-generated method stub
		/**
		salvar somente o bloco de save order cost
		
		colocar o user Id;
		
		teste de commit
		*/
		
	}
	
	

}
