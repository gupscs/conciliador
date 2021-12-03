package br.silveira.conciliador.integrator.service.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.integrator.dto.QueueDto;
import br.silveira.conciliador.integrator.entity.QueuePayments;
import br.silveira.conciliador.integrator.mapper.QueueDtoMapper;
import br.silveira.conciliador.integrator.repository.QueuePaymentsRepository;
import br.silveira.conciliador.integrator.service.QueuePaymentsService;

@Service
public class QueuePaymentsServiceImpl extends CommonServiceImpl implements QueuePaymentsService {

	private static final Logger log = LogManager.getLogger(QueuePaymentsServiceImpl.class);


	@Autowired
	private QueuePaymentsRepository queuePaymentsRepository;


	@Override
	public void saveQueuePayments(QueueDto dto) {
		dto = updateQueueDtoBasedMarketPlace(dto);
		QueuePayments queue = (QueuePayments) QueueDtoMapper.mapperToQueuePaymentsEntity(dto);
		QueuePayments save = queuePaymentsRepository.save(queue);
		log.info("queuePaymentsRepository created sucessfully, Id: " + save.getId());
	}



	@Override
	public void updateProcessStatusAndProcessMsg(QueueDto dto) throws Exception {
		Optional<QueuePayments> update = queuePaymentsRepository.findById(dto.getId());
		if (!update.isPresent()) {
			throw new Exception("Queue QueuePayments not found, id: " + dto.getId());
		}
		QueuePayments queue = update.get();
		queue.setProcessStatus(dto.getProcessStatus());
		queue.setProcessMsg(dto.getProcessMsg());
		queue.setUpdateDate(new Date());
		queue.setUpdateId(dto.getUpdateId());
		queuePaymentsRepository.save(queue);
	}

	@Override
	public void updateDocumentOriginalData(QueueDto dto) throws Exception {
		// TODO Auto-generated method stub
		Optional<QueuePayments> update = queuePaymentsRepository.findById(dto.getId());
		if (!update.isPresent()) {
			throw new Exception("Queue QueuePayments not found, id: " + dto.getId());
		}
		QueuePayments queue = update.get();
		queue.setDocumentOriginalData(dto.getDocumentOriginalData());
		queue.setUpdateDate(new Date());
		queue.setUpdateId(dto.getUpdateId());
		queuePaymentsRepository.save(queue);
	}

}
