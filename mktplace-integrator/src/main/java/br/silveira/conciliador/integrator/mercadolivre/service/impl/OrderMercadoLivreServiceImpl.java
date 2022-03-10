package br.silveira.conciliador.integrator.mercadolivre.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.silveira.conciliador.feignClient.dto.OrderDto;
import br.silveira.conciliador.feignClient.resource.OrderResource;
import br.silveira.conciliador.integrator.dto.OrderProcessDto;
import br.silveira.conciliador.integrator.dto.QueueDto;
import br.silveira.conciliador.integrator.mapper.QueueDtoMapper;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreNotificationDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto;
import br.silveira.conciliador.integrator.mercadolivre.mapper.MercadoLivreOrderDtoMapper;
import br.silveira.conciliador.integrator.service.OrderService;
import br.silveira.conciliador.integrator.service.QueueOrderService;

@Service("orderMercadoLivreServiceImpl")
public class OrderMercadoLivreServiceImpl extends MercadoLivreServiceCommon implements OrderService {
	
	private static final Logger log = LogManager.getLogger(OrderMercadoLivreServiceImpl.class);

	private static final String PROCESS_MSG_START_DOWNLOAD = "[Queue Id: %s] - Order Id: %s START DOWNLOAD";
	
	private static final String PROCESS_MSG_START_PROCESS = "[Queue Id: %s] - Order Id: %s START PROCESS";

	private static final String PROCESS_MSG_PROCESS_END = "[Queue Id: %s] - Order Id: %s PROCESS END";
	
	private static final String PROCESS_MSG_DOWNLOAD_END = "[Queue Id: %s] - Order Id: %s DOWNLOAD END";

	private static final String PROCESS_MSG_END_WITH_ERROR = "[Queue Id: %s] - Order Id: %s END (WITH ERRORS!!!)";

	private static final String PROCESS_MSG_NOT_FOUND = "[Queue Id: %s] - Order Id: %s not found, please check the market place";

	private static final String PROCESS_MSG_ON_GOING = "[Queue Id: %s] - Order Id: %s process start";

	private static final Integer PROCESS_STATUS_DOWNLOAD_DONE = 10;
	
	private static final Integer PROCESS_STATUS_ON_GOING = 1;

	private static final Integer PROCESS_STATUS_ERROR = 99;

	private static final Integer PROCESS_STATUS_SUCCESS = 100;

	@Autowired
	private OrderResource orderController;

	@Autowired
	private QueueOrderService queueOrderService;
	
	//TODO REFATORAR PARA ELIMINAR O DTO QueueDto E MANTER APENAS O OrderProcessDto , para isso encontrar um modo de fazer update em um campo especifico do mongodb
	
	@Override
	public void downloadOrder(OrderProcessDto orderProcessDto) {
		log.info(String.format(PROCESS_MSG_START_DOWNLOAD, orderProcessDto.getQueueOrdersId(), orderProcessDto.getDocumentId()));
		QueueDto dto = validateAndMapperToDto(orderProcessDto);
		try {
			updateProcessStatusOnGoing(dto);
			MercadoLivreOrderDto order = mercadoLivreService.getOrder(orderProcessDto.getApiToken(),orderProcessDto.getDocumentId());
			if (order == null) {
				updateQueueErrorNotFound(dto);
				log.error(String.format(PROCESS_MSG_END_WITH_ERROR, dto.getId(),dto.getDocumentId()));
			} else {
				dto.setDocumentOriginalData(order);
				orderProcessDto.setDocumentOriginalData(order);
				updateQueueSuccessDownload(dto);
				log.info(String.format(PROCESS_MSG_DOWNLOAD_END, dto.getId(),dto.getDocumentId()));
			}
		} catch (Exception e) {
			log.error(String.format(PROCESS_MSG_END_WITH_ERROR, dto.getId(),dto.getDocumentId()), e);
			updateQueueExceptionError(dto, e);
		}
	}
	

	@Override
	public void processOrder(OrderProcessDto orderProcessDto) {		
		log.info(String.format(PROCESS_MSG_START_PROCESS, orderProcessDto.getQueueOrdersId(), orderProcessDto.getDocumentId()));
		QueueDto dto = validateAndMapperToDto(orderProcessDto);
		
		try {
			updateProcessStatusOnGoing(dto);
			
			if(orderProcessDto.getDocumentOriginalData() == null) {
				downloadOrder(orderProcessDto);
			}
			
			MercadoLivreOrderDto order = (MercadoLivreOrderDto) orderProcessDto.getDocumentOriginalData();
			OrderDto orderDto = MercadoLivreOrderDtoMapper.mapperToOrderDto(order, dto);
			orderController.saveOrder(orderDto);

			updateQueueSuccessProcess(dto);
			log.info(String.format(PROCESS_MSG_PROCESS_END, dto.getId(),dto.getDocumentId()));
		
		} catch (Exception e) {
			log.error(String.format(PROCESS_MSG_END_WITH_ERROR, dto.getId(),dto.getDocumentId()), e);
			updateQueueExceptionError(dto, e);
		}
	}
	
	@Override
	public void processOrder(List<OrderProcessDto> dto) throws Exception {
		for (OrderProcessDto orderProcessDto : dto) {
			try {
				processOrder(orderProcessDto);
			}catch(Exception e) {
				log.warn("Error during process, check the logs!!!");
			}
		}
	}
	
	@Override
	public void downloadOrder(List<OrderProcessDto> orderProcessDtos) {
		for (OrderProcessDto orderProcessDto : orderProcessDtos) {
			try {
				downloadOrder(orderProcessDto);
			}catch(Exception e) {
				log.warn("Error during process, check the logs!!!");
			}
		}
		
	}
	
	

	private QueueDto validateAndMapperToDto(OrderProcessDto orderProcessDto) {
		if (orderProcessDto == null) {
			log.warn("Emtpy DTO!!!");
		}else if(!StringUtils.hasLength(orderProcessDto.getApiToken())) {
			try {
				MercadoLivreNotificationDto notificDto = (MercadoLivreNotificationDto) orderProcessDto.getNotificationOriginalData();
				orderProcessDto.setApiToken(getToken(orderProcessDto.getCompanyId(), notificDto.getUser_id()));
				return QueueDtoMapper.mapperFromOrderProcessDto(orderProcessDto);
			} catch (Exception e) {
				log.error(String.format(PROCESS_MSG_END_WITH_ERROR, orderProcessDto.getQueueOrdersId(),	orderProcessDto.getDocumentId()), e);
				updateQueueExceptionError(QueueDtoMapper.mapperFromOrderProcessDto(orderProcessDto), e);
			}
		}
		return null;
	}
	
	private void updateQueueSuccessDownload(QueueDto queueDto) {
		try {
			queueDto.setProcessMsg("");
			queueDto.setProcessStatus(PROCESS_STATUS_DOWNLOAD_DONE);
			queueDto.setUpdateDate(new Date());
			queueOrderService.updateDocumentOriginalDataAndProcessStatusAndProcessMsg(queueDto);
		} catch (Exception e1) {
			log.error("ERROR TO UPDATE STATUS", e1);
		}
		
	}

	private void updateQueueSuccessProcess(QueueDto queueDto) {
		try {
			queueDto.setProcessMsg("");
			queueDto.setProcessStatus(PROCESS_STATUS_SUCCESS);
			queueDto.setUpdateDate(new Date());
			queueOrderService.updateProcessStatusAndProcessMsg(queueDto);
		} catch (Exception e1) {
			log.error("ERROR TO UPDATE STATUS", e1);
		}
		
	}

	private void updateQueueExceptionError(QueueDto queueDto, Exception e) {
		try {
			queueDto.setProcessMsg(e.getMessage());
			queueDto.setProcessStatus(PROCESS_STATUS_ERROR);
			queueDto.setUpdateDate(new Date());
			queueOrderService.updateProcessStatusAndProcessMsg(queueDto);
		} catch (Exception e1) {
			log.error("ERROR TO UPDATE STATUS", e1);
		}
	}

	private void updateProcessStatusOnGoing(QueueDto queueDto) throws Exception {
		String msg = String.format(PROCESS_MSG_ON_GOING, queueDto.getId(), queueDto.getDocumentId());
		queueDto.setProcessMsg(msg);
		queueDto.setProcessStatus(PROCESS_STATUS_ON_GOING);
		queueDto.setUpdateDate(new Date());
		queueOrderService.updateProcessStatusAndProcessMsg(queueDto);
	}

	private void updateQueueErrorNotFound(QueueDto queueDto) throws Exception {
		String msg = String.format(PROCESS_MSG_NOT_FOUND, queueDto.getId(), queueDto.getDocumentId());
		log.warn(msg);
		queueDto.setProcessMsg(msg);
		queueDto.setProcessStatus(PROCESS_STATUS_ERROR);
		queueDto.setUpdateDate(new Date());
		queueOrderService.updateProcessStatusAndProcessMsg(queueDto);
	}

}
