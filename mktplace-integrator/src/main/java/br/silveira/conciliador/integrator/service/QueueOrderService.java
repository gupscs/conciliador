package br.silveira.conciliador.integrator.service;

import br.silveira.conciliador.integrator.dto.QueueDto;

public interface QueueOrderService {
	
	public void processAllQueueOrder(String companyId);
	
	public void processQueueOrder(String id);

	public void saveQueueOrders(QueueDto dto);

	public void updateProcessStatusAndProcessMsg(QueueDto dto) throws Exception;

	public void updateDocumentOriginalData(QueueDto dto) throws Exception;

}
