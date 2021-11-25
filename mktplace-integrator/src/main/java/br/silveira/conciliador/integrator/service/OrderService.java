package br.silveira.conciliador.integrator.service;

import java.util.List;

import br.silveira.conciliador.integrator.dto.OrderProcessDto;

public interface OrderService {
	
	public void processOrder(OrderProcessDto dto);

	public void processOrder(List<OrderProcessDto> dto, String companyId) throws Exception;

}
