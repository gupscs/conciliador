package br.silveira.conciliador.integrator.service.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.integrator.dto.QueueDto;
import br.silveira.conciliador.integrator.entity.QueueNotImplemented;
import br.silveira.conciliador.integrator.mapper.QueueDtoMapper;
import br.silveira.conciliador.integrator.repository.QueueNotImplementedRepository;
import br.silveira.conciliador.integrator.service.QueueNotImplementedService;

@Service
public class QueueNotImplementedServiceImpl extends CommonServiceImpl implements QueueNotImplementedService {

	private static final Logger log = LogManager.getLogger(QueueNotImplementedServiceImpl.class);

	@Autowired
	private QueueNotImplementedRepository queueNotImplementedRepository;


	@Override
	public void saveQueueNotImplemented(QueueDto dto) {
		dto = updateQueueDtoBasedMarketPlace(dto);
		QueueNotImplemented queueOrders = (QueueNotImplemented) QueueDtoMapper.mapperToQueueNotImplementedEntity(dto);
		QueueNotImplemented save = queueNotImplementedRepository.save(queueOrders);
		log.info("QueueNotImplemented created sucessfully, Id: " + save.getId());
	}

	@Override
	public void updateProcessStatusAndProcessMsg(QueueDto dto) throws Exception {
		Optional<QueueNotImplemented> update = queueNotImplementedRepository.findById(dto.getId());
		if (!update.isPresent()) {
			throw new Exception("Queue Not Implemented not found, id: " + dto.getId());
		}
		QueueNotImplemented queue = update.get();
		queue.setProcessStatus(dto.getProcessStatus());
		queue.setProcessMsg(dto.getProcessMsg());
		queue.setUpdateDate(new Date());
		queue.setUpdateId(dto.getUpdateId());
		queueNotImplementedRepository.save(queue);
	}

	@Override
	public void updateDocumentOriginalData(QueueDto dto) throws Exception {
		// TODO Auto-generated method stub
		Optional<QueueNotImplemented> update = queueNotImplementedRepository.findById(dto.getId());
		if (!update.isPresent()) {
			throw new Exception("Queue  Not Implemented not found, id: " + dto.getId());
		}
		QueueNotImplemented queue = update.get();
		queue.setDocumentOriginalData(dto.getDocumentOriginalData());
		queue.setUpdateDate(new Date());
		queue.setUpdateId(dto.getUpdateId());
		queueNotImplementedRepository.save(queue);
	}

}
