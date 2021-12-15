package br.silveira.conciliador.costcalc.ctr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.costcalc.dto.OrderCalculationDto;
import br.silveira.conciliador.costcalc.service.OrderCostCalculationService;

@RestController
@RequestMapping("/api/cost-calculation")
@CrossOrigin(origins = "*")
public class CostCalculationController {

	private static final Logger log = LogManager.getLogger(CostCalculationController.class);
	
	@Autowired
	private OrderCostCalculationService orderCostCalculationService;
	
	public ResponseEntity<?> calculeOrder(OrderCalculationDto dto) {
		try {
			orderCostCalculationService.calculateOrder(dto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Error to calculate the Order - DTO: "+dto, e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	public void recalculateAllPendingOrder(String companyId) {
		orderCostCalculationService.recalculateAllPendingOrder(companyId);
	}
	
	public void calculateReturnOrder(OrderCalculationDto dto) {
		orderCostCalculationService.calculateReturnOrder(dto);
	}
	
	
}
