package br.silveira.conciliador.orders.mapper;

import org.modelmapper.ModelMapper;

import br.silveira.conciliador.orders.dto.OrderDto;
import br.silveira.conciliador.orders.dto.OrderValuesDto;
import br.silveira.conciliador.orders.entity.Order;

public class OrderMapper {

	private static ModelMapper mapper = new ModelMapper();
	
	public static Order mapperToOrderEntity(OrderDto orderDto) {
		return mapper.map(orderDto , Order.class);
	}

	public static OrderValuesDto mapperToOrderValuesDto(Order order) {
		return mapper.map(order , OrderValuesDto.class);
	}

}
