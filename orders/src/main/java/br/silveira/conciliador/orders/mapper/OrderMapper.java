package br.silveira.conciliador.orders.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.silveira.conciliador.orders.dto.OrderCostCalculatedDto;
import br.silveira.conciliador.orders.dto.OrderDto;
import br.silveira.conciliador.orders.dto.OrderValuesDto;
import br.silveira.conciliador.orders.entity.FixedCostDetail;
import br.silveira.conciliador.orders.entity.ItemCostDetail;
import br.silveira.conciliador.orders.entity.Order;
import br.silveira.conciliador.orders.entity.OrderCostCalculated;

public class OrderMapper {

	private static ModelMapper mapper = new ModelMapper();
	
	public static Order mapperToOrderEntity(OrderDto orderDto) {
		return mapper.map(orderDto , Order.class);
	}

	public static OrderValuesDto mapperToOrderValuesDto(Order order) {
		return mapper.map(order , OrderValuesDto.class);
	}

	public static OrderCostCalculated mapperToOrderEntity(OrderCostCalculatedDto orderCostCalculatedDto) {
		OrderCostCalculated orderCost = mapper.map(orderCostCalculatedDto, OrderCostCalculated.class);
 
		List<FixedCostDetail> listFixed = orderCostCalculatedDto.getFixedCostDetail().stream().map(list -> mapper.map(list, FixedCostDetail.class)).collect(Collectors.toList());
		List<ItemCostDetail> listItem = orderCostCalculatedDto.getItemCostDetail().stream().map(list -> mapper.map(list, ItemCostDetail.class)).collect(Collectors.toList());
		orderCost.setFixedCostDetail(listFixed);
		orderCost.setItemCostDetail(listItem);
		
		return orderCost;
	}

}
