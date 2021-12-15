package br.silveira.conciliador.costcalc.service;

import br.silveira.conciliador.costcalc.dto.OrderCalculationDto;

public interface OrderCostCalculationService {

	public void calculateReturnOrder(OrderCalculationDto dto);

	public void calculateOrder(OrderCalculationDto dto) throws Exception;

	public void recalculateAllPendingOrder(String companyId);

}
