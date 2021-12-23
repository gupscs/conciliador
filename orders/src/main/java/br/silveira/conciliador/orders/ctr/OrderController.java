package br.silveira.conciliador.orders.ctr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.common.constant.RestTagConstant;
import br.silveira.conciliador.orders.dto.OrderCostDto;
import br.silveira.conciliador.orders.dto.OrderDto;
import br.silveira.conciliador.orders.dto.OrderValuesDto;
import br.silveira.conciliador.orders.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

	private static final Logger log = LogManager.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@PostMapping("/saveOrder")
	// @PreAutorize("hasAnyRole('ORDERS')")
	public ResponseEntity<Void> saveOrder(@RequestBody OrderDto orderDto) {
		try {
			orderService.saveOrder(orderDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Error to save Orders: " + orderDto, e);
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@PostMapping("/saveOrderCost")
	// @PreAutorize("hasAnyRole('ORDERS')")
	public ResponseEntity<Void> saveOrderCost(@RequestBody OrderCostDto orderCostDto) {
		try {
			orderService.saveOrderCost(orderCostDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Error to save Orders: " + orderCostDto, e);
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@GetMapping("/getOrderValues/{id}")
	// @PreAutorize("hasAnyRole('ORDERS')")
	public ResponseEntity<OrderValuesDto> getOrderValues(@PathVariable(value = "id") String orderId) {
		try {
			OrderValuesDto dto = orderService.getOrderValues(orderId);
			if (dto == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok(dto);
			}
		} catch (Exception e) {
			log.error("Error to get order values - Id: " + orderId, e);
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}
}
