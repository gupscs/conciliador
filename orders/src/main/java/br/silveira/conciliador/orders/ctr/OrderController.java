package br.silveira.conciliador.orders.ctr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.common.dto.RestResponseDto;
import br.silveira.conciliador.orders.dto.OrderDto;
import br.silveira.conciliador.orders.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

	private static final Logger log = LogManager.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;

	
	
	@PostMapping("/saveOrder")
	// @PreAutorize("hasAnyRole('ORDERS')")
	public ResponseEntity<RestResponseDto<?>> saveOrder(@RequestBody OrderDto orderDto) {
		try {
			orderService.saveOrder(orderDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Error to save Orders: "+orderDto, e);
			return ResponseEntity.badRequest().body(new RestResponseDto<>(e.getMessage()));
		}
	}
}
