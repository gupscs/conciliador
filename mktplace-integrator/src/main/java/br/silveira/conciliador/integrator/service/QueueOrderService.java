package br.silveira.conciliador.integrator.service;

import br.silveira.conciliador.integrator.dto.QueueDto;

public interface QueueOrderService {
	
	public void processAllQueueOrder(String companyId);
	
	public void processQueueOrder(String id) throws Exception;

	public void saveQueueOrders(QueueDto dto);

	public void updateProcessStatusAndProcessMsg(QueueDto dto) throws Exception;

	public void updateDocumentOriginalDataAndProcessStatusAndProcessMsg(QueueDto dto) throws Exception;

	public void downloadAllQueueOrder(String companyId);

	public void downloadQueueOrder(String queueOrderId) throws Exception;

}
