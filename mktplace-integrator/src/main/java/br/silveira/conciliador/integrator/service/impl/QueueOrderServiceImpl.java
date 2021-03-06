package br.silveira.conciliador.integrator.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.integrator.dto.OrderProcessDto;
import br.silveira.conciliador.integrator.dto.QueueDto;
import br.silveira.conciliador.integrator.entity.QueueOrders;
import br.silveira.conciliador.integrator.factories.OrderServiceFactory;
import br.silveira.conciliador.integrator.mapper.QueueDtoMapper;
import br.silveira.conciliador.integrator.repository.QueueOrdersRepository;
import br.silveira.conciliador.integrator.service.QueueOrderService;

@Service
public class QueueOrderServiceImpl extends CommonServiceImpl implements QueueOrderService {
	
	private static final Logger log = LogManager.getLogger(QueueOrderServiceImpl.class);
	
	private static final Integer PENDING_PROCESS_STATUS = 0;
	
	private static final Integer DOWNLOAD_DONE_STATUS = 10;
	
	private static final String QUEUE_ID_NOT_FOUND = "Order Queue Id: %s not found";
	
	@Autowired
	private OrderServiceFactory orderServiceFactory;
	
	@Autowired
	private QueueOrdersRepository queueOrdersRepository;
	
	@Override
	public void saveQueueOrders(QueueDto dto) {
		dto = updateQueueDtoBasedMarketPlace(dto);
		QueueOrders queueOrders =  QueueDtoMapper.mapperToQueueOrdersEntity(dto);
		QueueOrders save = queueOrdersRepository.save(queueOrders);
		log.info("QueueOrder created sucessfully, Id: " + save.getId());
	}
	
	@Override
	public void updateProcessStatusAndProcessMsg(QueueDto dto) throws Exception {
		Optional<QueueOrders> update = queueOrdersRepository.findById(dto.getId());
		if (!update.isPresent()) {
			throw new Exception("Queue Order not found, id: " + dto.getId());
		}
		QueueOrders queueOrders = update.get();
		queueOrders.setProcessStatus(dto.getProcessStatus());
		queueOrders.setProcessMsg(dto.getProcessMsg());
		queueOrders.setUpdateDate(new Date());
		queueOrders.setUpdateId(dto.getUpdateId());
		queueOrdersRepository.save(queueOrders);
	}

	@Override
	public void updateDocumentOriginalDataAndProcessStatusAndProcessMsg(QueueDto dto) throws Exception {
		// TODO Auto-generated method stub
		Optional<QueueOrders> update = queueOrdersRepository.findById(dto.getId());
		if (!update.isPresent()) {
			throw new Exception("Queue Order not found, id: " + dto.getId());
		}
		QueueOrders queueOrders = update.get();
		queueOrders.setDocumentOriginalData(dto.getDocumentOriginalData());
		queueOrders.setProcessStatus(dto.getProcessStatus());
		queueOrders.setProcessMsg(dto.getProcessMsg());
		queueOrders.setUpdateDate(new Date());
		queueOrders.setUpdateId(dto.getUpdateId());
		queueOrdersRepository.save(queueOrders);
	}

	@Override
	public void processAllQueueOrder(String companyId) {
		Integer[] processStatus = new Integer[]{PENDING_PROCESS_STATUS, DOWNLOAD_DONE_STATUS};
		List<QueueOrders> queueOrdersPending = queueOrdersRepository.findByCompanyIdAndListProcessStatus(companyId, Arrays.asList(processStatus));
		if(queueOrdersPending != null && queueOrdersPending.isEmpty()) {
			Map<MktPlaceEnum, List<OrderProcessDto>> orderSeparatedByMktPlace = converteEntityToMap(queueOrdersPending);
			for (MktPlaceEnum mktPlaceEnum : orderSeparatedByMktPlace.keySet()) {
				try {
					orderServiceFactory.getImpl(mktPlaceEnum).processOrder( orderSeparatedByMktPlace.get(mktPlaceEnum));
				}catch(Exception e) {
					log.error("Error to process All Queueu Order for Company Id: "+companyId, e);
				}
			}
		}
	}

	private Map<MktPlaceEnum, List<OrderProcessDto>> converteEntityToMap(List<QueueOrders> queueOrdersPending) {
		List<OrderProcessDto> ordersProcess = QueueDtoMapper.mapperToOrderProcessDto(queueOrdersPending);
		Map<MktPlaceEnum, List<OrderProcessDto>> ret = new HashMap<MktPlaceEnum, List<OrderProcessDto>>();
		for (OrderProcessDto dto : ordersProcess) {
			MktPlaceEnum marketPlace = dto.getMarketPlace();
			if(!ret.containsKey(marketPlace)) {
				ret.put(marketPlace, new ArrayList<OrderProcessDto>());
			}
			ret.get(marketPlace).add(dto);
		}
		return ret;
	}

	@Override
	public void processQueueOrder(String id) throws Exception {
		Optional<QueueOrders> entity = queueOrdersRepository.findById(id);
		if(entity.isPresent()) {
			 OrderProcessDto dto = QueueDtoMapper.mapperToOrderProcessDto(entity.get());
			 orderServiceFactory.getImpl(dto.getMarketPlace()).processOrder(dto);	
		}else {
			throw new Exception(String.format(QUEUE_ID_NOT_FOUND, id));
		}

	}

	@Override
	public void downloadAllQueueOrder(String companyId) {
		List<QueueOrders> queueOrdersPending = queueOrdersRepository.findByCompanyIdAndProcessStatus(companyId, PENDING_PROCESS_STATUS);
		if(queueOrdersPending != null && !queueOrdersPending.isEmpty()) {
			Map<MktPlaceEnum, List<OrderProcessDto>> orderSeparatedByMktPlace = converteEntityToMap(queueOrdersPending);
			for (MktPlaceEnum mktPlaceEnum : orderSeparatedByMktPlace.keySet()) {
				try {
					orderServiceFactory.getImpl(mktPlaceEnum).downloadOrder( orderSeparatedByMktPlace.get(mktPlaceEnum));
				}catch(Exception e) {
					log.error("Error to download All Queue Order for Company Id: "+companyId, e);
				}
			}
		}
		
	}

	@Override
	public void downloadQueueOrder(String queueOrderId) throws Exception {
		Optional<QueueOrders> entity = queueOrdersRepository.findById(queueOrderId);
		if(entity.isPresent()) {
			 OrderProcessDto dto = QueueDtoMapper.mapperToOrderProcessDto(entity.get());
			 orderServiceFactory.getImpl(dto.getMarketPlace()).downloadOrder(dto);	
		}else {
			throw new Exception(String.format(QUEUE_ID_NOT_FOUND, queueOrderId));
		}
		
	}
}
