package br.silveira.conciliador.orders.ctr;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.silveira.conciliador.orders.dto.OrderDto;

@FeignClient(name = "orderController", url = "${order.url}")
public interface OrderController {
	
	
	@PostMapping("/saveOrder")
	public void saveOrder(@RequestBody OrderDto orderDto);

}
