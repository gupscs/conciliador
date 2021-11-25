package br.silveira.conciliador.orders.ctr;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.silveira.conciliador.orders.dto.OrderDto;

@FeignClient(name = "orderController", url = "${order.url}")
public interface OrderController {
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveOrder", produces = "application/json")
	public void saveOrder(@RequestBody OrderDto orderDto);

}
