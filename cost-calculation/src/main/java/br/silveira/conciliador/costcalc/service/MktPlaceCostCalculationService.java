package br.silveira.conciliador.costcalc.service;

import br.silveira.conciliador.costcalc.dto.OrderCalculationResultDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationValuesDto;

public interface MktPlaceCostCalculationService {
	
	public OrderCalculationResultDto calculateOrder(OrderCalculationValuesDto dto);

}
