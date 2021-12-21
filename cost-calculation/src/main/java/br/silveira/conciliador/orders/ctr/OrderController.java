package br.silveira.conciliador.orders.ctr;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.silveira.conciliador.orders.dto.OrderCostDto;
import br.silveira.conciliador.orders.dto.OrderDto;
import br.silveira.conciliador.orders.dto.OrderValuesDto;

@FeignClient(name = "orderController", url = "${order.url}")
public interface OrderController {


	@PostMapping("/saveOrder")
	public ResponseEntity<Void> saveOrder(@RequestBody OrderDto orderDto) ;

	@PostMapping("/saveOrderCost")
	public ResponseEntity<Void> saveOrderCost(@RequestBody OrderCostDto orderCostDto) ;

	@GetMapping("/getOrderValues/{id}")
	public ResponseEntity<OrderValuesDto> getOrderValues(@PathVariable(value = "id") String orderId) ;
}
