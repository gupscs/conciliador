package br.silveira.conciliador.integrator.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.integrator.dto.QueueDto;
import br.silveira.conciliador.integrator.entity.QueueFeedbacks;
import br.silveira.conciliador.integrator.mapper.QueueDtoMapper;
import br.silveira.conciliador.integrator.repository.QueueFeedbacksRepository;
import br.silveira.conciliador.integrator.service.QueueFeedbackService;

@Service
public class QueueFeedbackServiceImpl extends CommonServiceImpl implements QueueFeedbackService {

	private static final Logger log = LogManager.getLogger(QueueFeedbackServiceImpl.class);

	@Autowired
	private QueueFeedbacksRepository queueFeedbacksRepository;


	@Override
	public void saveQueueFeedbacks(QueueDto dto) {
		dto = updateQueueDtoBasedMarketPlace(dto);
		QueueFeedbacks queue = (QueueFeedbacks) QueueDtoMapper.mapperToQueueFeedbacksEntity(dto);
		QueueFeedbacks save = queueFeedbacksRepository.save(queue);
		log.info("QueueFeedbacks created sucessfully, Id: " + save.getId());
	}


	@Override
	public void updateProcessStatusAndProcessMsg(QueueDto dto) throws Exception {
		Optional<QueueFeedbacks> update = queueFeedbacksRepository.findById(dto.getId());
		if (!update.isPresent()) {
			throw new Exception("Queue Order not found, id: " + dto.getId());
		}
		QueueFeedbacks queue = update.get();
		queue.setProcessStatus(dto.getProcessStatus());
		queue.setProcessMsg(dto.getProcessMsg());
		queue.setUpdateDate(LocalDateTime.now());
		queue.setUpdateId(dto.getUpdateId());
		queueFeedbacksRepository.save(queue);
	}

	@Override
	public void updateDocumentOriginalData(QueueDto dto) throws Exception {
		// TODO Auto-generated method stub
		Optional<QueueFeedbacks> update = queueFeedbacksRepository.findById(dto.getId());
		if (!update.isPresent()) {
			throw new Exception("Queue Feedback not found, id: " + dto.getId());
		}
		QueueFeedbacks queue = update.get();
		queue.setDocumentOriginalData(dto.getDocumentOriginalData());
		queue.setUpdateDate(LocalDateTime.now());
		queue.setUpdateId(dto.getUpdateId());
		queueFeedbacksRepository.save(queue);
	}

}
