package br.silveira.conciliador.orders.ctr;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.silveira.conciliador.common.dto.RestResponseDto;
import br.silveira.conciliador.orders.dto.OrderCostDto;
import br.silveira.conciliador.orders.dto.OrderDto;
import br.silveira.conciliador.orders.dto.OrderValuesDto;

@FeignClient(name = "orderController", url = "${order.url}")
public interface OrderController {
	
	
	@PostMapping("/saveOrder")
	public void saveOrder(@RequestBody OrderDto orderDto);
	

	@GetMapping("/getOrderValues/{id}")
	public RestResponseDto<OrderValuesDto> getOrderValues(@PathVariable(value="id") String orderId);
	
	@PostMapping("/saveOrderCost")
	public void saveOrderCost(@RequestBody OrderCostDto orderCostDto) ;

}
