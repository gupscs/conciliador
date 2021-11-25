package br.silveira.conciliador.integrator.service;

import br.silveira.conciliador.integrator.dto.QueueDto;

public interface QueueShipmentsService {

	public void saveQueueShipments(QueueDto dto);

	public void updateProcessStatusAndProcessMsg(QueueDto dto) throws Exception;

	public void updateDocumentOriginalData(QueueDto dto) throws Exception;

}
