package br.silveira.conciliador.costcalc.ctr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.costcalc.dto.OrderCalculationDto;
import br.silveira.conciliador.costcalc.service.OrderCostCalculationService;

@RestController
@RequestMapping("/api/cost-calculation")
@CrossOrigin(origins = "*")
public class CostCalculationController {

	@Autowired
	private OrderCostCalculationService orderCostCalculationService;
	
	public void calculeOrder(OrderCalculationDto dto) {
		orderCostCalculationService.calculateOrder(dto);
	}
	
	
	public void recalculateAllPendingOrder(String companyId) {
		orderCostCalculationService.recalculateAllPendingOrder(companyId);
	}
	
	public void calculateReturnOrder(OrderCalculationDto dto) {
		orderCostCalculationService.calculateReturnOrder(dto);
	}
	
	
}
