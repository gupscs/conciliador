package br.silveira.conciliador.feignClient.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.silveira.conciliador.feignClient.dto.OrderCostCalculatedDto;
import br.silveira.conciliador.feignClient.dto.OrderDto;
import br.silveira.conciliador.feignClient.dto.OrderValuesDto;

@FeignClient(name = "orders")
public interface OrderResource {


	@PostMapping("/orders/saveOrder")
	public ResponseEntity<Void> saveOrder(@RequestBody OrderDto orderDto) ;

	@PostMapping("/orders/saveOrderCostCalculated")
	public ResponseEntity<Void> saveOrderCostCalculated(@RequestBody OrderCostCalculatedDto orderCostCalculatedDto) ;

	@GetMapping("/orders/getOrderValues/{id}")
	public ResponseEntity<OrderValuesDto> getOrderValues(@PathVariable(value = "id") String orderId) ;
}
