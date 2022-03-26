package br.silveira.conciliador.feignClient.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.silveira.conciliador.feignClient.dto.OrderDto;

@FeignClient(name = "orders")
public interface OrderResource {


	@PostMapping("/orders/saveOrder")
	public ResponseEntity<Void> saveOrder(@RequestBody OrderDto orderDto) ;

}
