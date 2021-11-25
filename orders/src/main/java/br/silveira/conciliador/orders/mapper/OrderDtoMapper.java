package br.silveira.conciliador.orders.mapper;

import org.modelmapper.ModelMapper;

import br.silveira.conciliador.orders.dto.OrderDto;
import br.silveira.conciliador.orders.entity.Order;

public class OrderDtoMapper {

	private static ModelMapper mapper = new ModelMapper();
	
	public static Order mapperToOrderEntity(OrderDto orderDto) {
		return mapper.map(orderDto , Order.class);
	}

}
