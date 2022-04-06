package br.silveira.conciliador.integrator.mercadolivre.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.integrator.entity.QueueShipments;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreNotificationDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreShipmentCostDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreShipmentDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreShippingOptionsDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreShippingOptionsDto.Option;
import br.silveira.conciliador.integrator.mercadolivre.service.MercadoLivreRestService;
import br.silveira.conciliador.integrator.mercadolivre.service.ShipmentMercadoLivreService;
import br.silveira.conciliador.integrator.repository.QueueShipmentsRepository;

@Service
public class ShipmentMercadoLivreServiceImpl extends MercadoLivreServiceCommon implements ShipmentMercadoLivreService {

	private static final Logger log = LogManager.getLogger(ShipmentMercadoLivreServiceImpl.class);
	
	private static final Integer PROCESS_STATUS_DOWNLOAD_DONE = 10;

	@Autowired
	private QueueShipmentsRepository queueShipmentsRepository;

	@Autowired
	private MercadoLivreRestService mercadoLivreRestService;

	@Override
	public void downloadAllShipmentsByCompanyId(String companyId) {
		List<QueueShipments> queueShipments = queueShipmentsRepository.findByCompanyIdAndProcessStatus(companyId, 0);
		for (QueueShipments s : queueShipments) {
			try {
				MercadoLivreNotificationDto notificDto = (MercadoLivreNotificationDto) s.getNotificationOriginalData();
				String token = getToken(s.getCompanyId(), notificDto.getUser_id());

				Object shipment = mercadoLivreRestService.getShipment(token, s.getDocumentId());

				s.setDocumentOriginalData(shipment);
				s.setProcessStatus(PROCESS_STATUS_DOWNLOAD_DONE);
				s.setUpdateDate(new Date());
				s.setUpdateId("DRAFT_SHIPMENT_PROCESS");

				queueShipmentsRepository.save(s);

			} catch (Exception e) {
				log.error("Error to download shipment", e);
			}
		}
	}

	@Override
	public MercadoLivreShipmentCostDto getShippingCostByItemCode(String companyId, Long mktPlaceUserId, String itemId, String zipcode, Double quantity, Integer shippingMethodId) throws Exception {
		String token = getToken(companyId, mktPlaceUserId);
		MercadoLivreShippingOptionsDto shippingOptionsByItemId = mercadoLivreRestService.getShippingOptionsByItemId(token, itemId, zipcode, quantity);
		Optional<Option> optionFirst = shippingOptionsByItemId.getOptions().stream().filter(o -> o.getShipping_method_id() == shippingMethodId).findFirst();
		if (optionFirst.isPresent()) {
			return new MercadoLivreShipmentCostDto(companyId, mktPlaceUserId, itemId, quantity, optionFirst.get().getCost());
		} else {
			return null;
		}
	}

	@Override
	public QueueShipments downloadShipmentByQueueShipments(QueueShipments queueShipments) throws Exception {
			MercadoLivreNotificationDto notificDto = (MercadoLivreNotificationDto) queueShipments.getNotificationOriginalData();
			String token = getToken(queueShipments.getCompanyId(), notificDto.getUser_id());

			MercadoLivreShipmentDto shipment = mercadoLivreRestService.getShipment(token, queueShipments.getDocumentId());

			queueShipments.setDocumentOriginalData(shipment);
			queueShipments.setProcessStatus(PROCESS_STATUS_DOWNLOAD_DONE);
			queueShipments.setUpdateDate(new Date());
			queueShipments.setUpdateId("AUTO");

			queueShipmentsRepository.save(queueShipments);
			
			return queueShipments;
	}

	@Override
	public MercadoLivreShipmentDto getShipmentByCompanyIdAndMktPlaceAndShipmentId(String companyId, MktPlaceEnum mktPlace, String shipmentId) throws Exception {
		List<QueueShipments> shipmentQueue = queueShipmentsRepository.findByCompanyIdAndMarketPlaceAndDocumentId(companyId, mktPlace, shipmentId);
		if (shipmentQueue == null || shipmentQueue.isEmpty()) {
			return null;
		} else {
			QueueShipments lastQueue = getLastQueueShipments(shipmentQueue);
			if (lastQueue.getDocumentOriginalData() == null) {
				lastQueue = downloadShipmentByQueueShipments(lastQueue);
			} 
			return (MercadoLivreShipmentDto) lastQueue.getDocumentOriginalData();
		}
	}

	private QueueShipments getLastQueueShipments(List<QueueShipments> shipmentQueue) {
		QueueShipments lastQueue = null;
		for (QueueShipments qs : shipmentQueue) {
			if (lastQueue == null) {
				lastQueue = qs;
			} else if (lastQueue.getInsertDate().before(qs.getInsertDate())) {
				lastQueue = qs;
			}
		}
		return lastQueue;
	}

}
