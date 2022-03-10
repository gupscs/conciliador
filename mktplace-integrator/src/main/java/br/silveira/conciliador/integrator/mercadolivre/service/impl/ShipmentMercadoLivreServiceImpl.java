package br.silveira.conciliador.integrator.mercadolivre.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.integrator.entity.QueueShipments;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreNotificationDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreShipmentDto;
import br.silveira.conciliador.integrator.mercadolivre.service.MercadoLivreRestService;
import br.silveira.conciliador.integrator.repository.QueueShipmentsRepository;

@Service
public class ShipmentMercadoLivreServiceImpl extends MercadoLivreServiceCommon {
	
	private static final Logger log = LogManager.getLogger(ShipmentMercadoLivreServiceImpl.class);

	@Autowired
	private QueueShipmentsRepository queueShipmentsRepository;

	@Autowired
	private MercadoLivreRestService mercadoLivreRestService;

	public void downloadShipment(String companyId) {
		List<QueueShipments> queueShipments = queueShipmentsRepository.findByCompanyIdAndProcessStatus(companyId, 0);
		for (QueueShipments s : queueShipments) {
			try {
				MercadoLivreNotificationDto notificDto = (MercadoLivreNotificationDto) s.getNotificationOriginalData();
				String token = getToken(s.getCompanyId(), notificDto.getUser_id());

				Object shipment = mercadoLivreRestService.getShipment(token, s.getDocumentId());
				
				s.setDocumentOriginalData(shipment);
				s.setProcessStatus(10);
				s.setUpdateDate(new Date());
				s.setUpdateId("DRAFT_SHIPMENT_PROCESS");
				
				queueShipmentsRepository.save(s);
				
			} catch (Exception e) {
				log.error("Error to download shipment" , e);
			}
		}
	}

}
