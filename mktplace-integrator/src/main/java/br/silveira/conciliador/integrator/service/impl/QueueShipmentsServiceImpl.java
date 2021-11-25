package br.silveira.conciliador.integrator.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.integrator.dto.QueueDto;
import br.silveira.conciliador.integrator.entity.QueueShipments;
import br.silveira.conciliador.integrator.mapper.QueueDtoMapper;
import br.silveira.conciliador.integrator.repository.QueueShipmentsRepository;
import br.silveira.conciliador.integrator.service.QueueShipmentsService;

@Service
public class QueueShipmentsServiceImpl extends CommonServiceImpl implements QueueShipmentsService {

	private static final Logger log = LogManager.getLogger(QueueShipmentsServiceImpl.class);

	@Autowired
	private QueueShipmentsRepository queueShipmentsRepository;



	@Override
	public void saveQueueShipments(QueueDto dto) {
		dto = updateQueueDtoBasedMarketPlace(dto);
		QueueShipments queue = (QueueShipments) QueueDtoMapper.mapperToQueueShipmentsEntity(dto);
		QueueShipments save = queueShipmentsRepository.save(queue);
		log.info("QueueShipments created sucessfully, Id: " + save.getId());
	}



	@Override
	public void updateProcessStatusAndProcessMsg(QueueDto dto) throws Exception {
		Optional<QueueShipments> update = queueShipmentsRepository.findById(dto.getId());
		if (!update.isPresent()) {
			throw new Exception("Queue Order not found, id: " + dto.getId());
		}
		QueueShipments queue = update.get();
		queue.setProcessStatus(dto.getProcessStatus());
		queue.setProcessMsg(dto.getProcessMsg());
		queue.setUpdateDate(LocalDateTime.now());
		queue.setUpdateId(dto.getUpdateId());
		queueShipmentsRepository.save(queue);
	}

	@Override
	public void updateDocumentOriginalData(QueueDto dto) throws Exception {
		// TODO Auto-generated method stub
		Optional<QueueShipments> update = queueShipmentsRepository.findById(dto.getId());
		if (!update.isPresent()) {
			throw new Exception("Queue Order not found, id: " + dto.getId());
		}
		QueueShipments queue = update.get();
		queue.setDocumentOriginalData(dto.getDocumentOriginalData());
		queue.setUpdateDate(LocalDateTime.now());
		queue.setUpdateId(dto.getUpdateId());
		queueShipmentsRepository.save(queue);
	}

}
