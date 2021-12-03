package br.silveira.conciliador.integrator.mapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.integrator.dto.OrderProcessDto;
import br.silveira.conciliador.integrator.dto.QueueDto;
import br.silveira.conciliador.integrator.entity.QueueCommon;
import br.silveira.conciliador.integrator.entity.QueueFeedbacks;
import br.silveira.conciliador.integrator.entity.QueueNotImplemented;
import br.silveira.conciliador.integrator.entity.QueueOrders;
import br.silveira.conciliador.integrator.entity.QueuePayments;
import br.silveira.conciliador.integrator.entity.QueueShipments;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreNotificationDto;

public class QueueDtoMapper {

	private static ModelMapper mapper = new ModelMapper();
	
	private static final String INSERT_ID_AUTO = "AUTO";

	public static QueueCommon mapperToQueueCommonEntity(QueueDto dto) {
		return mapper.map(dto, QueueCommon.class);
	}

	public static QueueDto mapperFromMercadoLivreNotification(MercadoLivreNotificationDto notificationDto) {
		QueueDto dto = new QueueDto();
		dto.setMktPlaceUserId(""+notificationDto.getUser_id());
		dto.setDocumentId(parseDocumentId(notificationDto));
		dto.setInsertDate(new Date());
		dto.setMarketPlace(MktPlaceEnum.MERCADO_LIVRE);
		dto.setNotificationOriginalData(notificationDto);
		dto.setProcessStatus(0);
		dto.setInsertId("MERCADO_LIVRE_NOTIFICATION");
		dto.setApplicationId(notificationDto.getApplication_id().toString());

		return dto;
	}

	private static String parseDocumentId(MercadoLivreNotificationDto notificationDto) {
		String[] split = notificationDto.getResource().split("/");
		return split[split.length - 1];
	}

	public static QueueDto mapperFromOrderProcessDto(OrderProcessDto orderProcessDto) {
		QueueDto dto = new QueueDto();
		dto.setId(orderProcessDto.getQueueOrdersId());
		dto.setCompanyId(orderProcessDto.getCompanyId());
		dto.setMarketPlace(orderProcessDto.getMarketPlace());
		dto.setDocumentId(orderProcessDto.getDocumentId());
		dto.setApplicationId(orderProcessDto.getApplicationId());
		dto.setUpdateId(orderProcessDto.getExecutionId());
		dto.setInsertId(orderProcessDto.getExecutionId());
		return dto;
	}

	public static QueueOrders mapperToQueueOrdersEntity(QueueDto dto) {
		QueueOrders entity = new QueueOrders();
		entity.setCompanyId(dto.getCompanyId());
		entity.setMarketPlace(dto.getMarketPlace());
		entity.setNotificationOriginalData(dto.getNotificationOriginalData());
		entity.setDocumentOriginalData(dto.getDocumentOriginalData());
		entity.setApplicationId(dto.getApplicationId());
		entity.setDocumentId(dto.getDocumentId());
		entity.setProcessStatus(dto.getProcessStatus()==null?0:dto.getProcessStatus());
		entity.setProcessMsg(dto.getProcessMsg());
		entity.setInsertDate(dto.getInsertDate()==null?new Date():dto.getInsertDate());
		entity.setInsertId(dto.getInsertId()==null?INSERT_ID_AUTO:dto.getInsertId());
		entity.setUpdateDate(dto.getUpdateDate());
		entity.setUpdateId(dto.getUpdateId());
		entity.setId(dto.getId());
		return entity;

	}

	public static QueueShipments mapperToQueueShipmentsEntity(QueueDto dto) {
		QueueShipments entity = new QueueShipments();
		entity.setCompanyId(dto.getCompanyId());
		entity.setMarketPlace(dto.getMarketPlace());
		entity.setNotificationOriginalData(dto.getNotificationOriginalData());
		entity.setDocumentOriginalData(dto.getDocumentOriginalData());
		entity.setApplicationId(dto.getApplicationId());
		entity.setDocumentId(dto.getDocumentId());
		entity.setProcessStatus(dto.getProcessStatus()==null?0:dto.getProcessStatus());
		entity.setProcessMsg(dto.getProcessMsg());
		entity.setInsertDate(dto.getInsertDate()==null?new Date():dto.getInsertDate());
		entity.setInsertId(dto.getInsertId()==null?INSERT_ID_AUTO:dto.getInsertId());
		entity.setUpdateDate(dto.getUpdateDate());
		entity.setUpdateId(dto.getUpdateId());
		entity.setId(dto.getId());
		return entity;
	}

	public static QueuePayments mapperToQueuePaymentsEntity(QueueDto dto) {
		QueuePayments entity = new QueuePayments();
		entity.setCompanyId(dto.getCompanyId());
		entity.setMarketPlace(dto.getMarketPlace());
		entity.setNotificationOriginalData(dto.getNotificationOriginalData());
		entity.setDocumentOriginalData(dto.getDocumentOriginalData());
		entity.setApplicationId(dto.getApplicationId());
		entity.setDocumentId(dto.getDocumentId());
		entity.setProcessStatus(dto.getProcessStatus()==null?0:dto.getProcessStatus());
		entity.setProcessMsg(dto.getProcessMsg());
		entity.setInsertDate(dto.getInsertDate()==null?new Date():dto.getInsertDate());
		entity.setInsertId(dto.getInsertId()==null?INSERT_ID_AUTO:dto.getInsertId());
		entity.setUpdateDate(dto.getUpdateDate());
		entity.setUpdateId(dto.getUpdateId());
		entity.setId(dto.getId());
		return entity;
	}

	public static QueueFeedbacks mapperToQueueFeedbacksEntity(QueueDto dto) {
		QueueFeedbacks entity = new QueueFeedbacks();
		entity.setCompanyId(dto.getCompanyId());
		entity.setMarketPlace(dto.getMarketPlace());
		entity.setNotificationOriginalData(dto.getNotificationOriginalData());
		entity.setDocumentOriginalData(dto.getDocumentOriginalData());
		entity.setApplicationId(dto.getApplicationId());
		entity.setDocumentId(dto.getDocumentId());
		entity.setProcessStatus(dto.getProcessStatus()==null?0:dto.getProcessStatus());
		entity.setProcessMsg(dto.getProcessMsg());
		entity.setInsertDate(dto.getInsertDate()==null?new Date():dto.getInsertDate());
		entity.setInsertId(dto.getInsertId()==null?INSERT_ID_AUTO:dto.getInsertId());
		entity.setUpdateDate(dto.getUpdateDate());
		entity.setUpdateId(dto.getUpdateId());
		entity.setId(dto.getId());
		return entity;
	}

	public static QueueNotImplemented mapperToQueueNotImplementedEntity(QueueDto dto) {
		QueueNotImplemented entity = new QueueNotImplemented();
		entity.setCompanyId(dto.getCompanyId());
		entity.setMarketPlace(dto.getMarketPlace());
		entity.setNotificationOriginalData(dto.getNotificationOriginalData());
		entity.setDocumentOriginalData(dto.getDocumentOriginalData());
		entity.setApplicationId(dto.getApplicationId());
		entity.setDocumentId(dto.getDocumentId());
		entity.setProcessStatus(dto.getProcessStatus()==null?0:dto.getProcessStatus());
		entity.setProcessMsg(dto.getProcessMsg());
		entity.setInsertDate(dto.getInsertDate()==null?new Date():dto.getInsertDate());
		entity.setInsertId(dto.getInsertId()==null?INSERT_ID_AUTO:dto.getInsertId());
		entity.setUpdateDate(dto.getUpdateDate());
		entity.setUpdateId(dto.getUpdateId());
		entity.setId(dto.getId());
		return entity;
	}

	public static List<OrderProcessDto> mapperToOrderProcessDto(List<QueueOrders> queueOrders) {
		return queueOrders.stream().map(queue -> mapperToOrderProcessDto(queue)).collect(Collectors.toList());
	}

	public static OrderProcessDto mapperToOrderProcessDto(QueueOrders queueOrder) {
		OrderProcessDto dto = new OrderProcessDto();
		dto.setApiToken(null);
		dto.setApplicationId(queueOrder.getApplicationId());
		dto.setCompanyId(queueOrder.getCompanyId());
		dto.setDocumentId(queueOrder.getDocumentId());
		dto.setExecutionId("");
		dto.setMarketPlace(queueOrder.getMarketPlace());
		dto.setQueueOrdersId(queueOrder.getId());
		dto.setNotificationOriginalData(queueOrder.getNotificationOriginalData());
		dto.setDocumentOriginalData(queueOrder.getDocumentOriginalData());
		return dto;
	}

}
