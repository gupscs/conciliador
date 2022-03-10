package br.silveira.conciliador.integrator.mercadolivre.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.integrator.entity.QueuePayments;
import br.silveira.conciliador.integrator.entity.QueueShipments;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreNotificationDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreShipmentDto;
import br.silveira.conciliador.integrator.mercadolivre.service.MercadoLivreRestService;
import br.silveira.conciliador.integrator.repository.QueuePaymentsRepository;
import br.silveira.conciliador.integrator.repository.QueueShipmentsRepository;

@Service
public class PaymentMercadoLivreServiceImpl extends MercadoLivreServiceCommon {
	
	private static final Logger log = LogManager.getLogger(PaymentMercadoLivreServiceImpl.class);

	@Autowired
	private QueuePaymentsRepository queuePaymentsRepository;

	@Autowired
	private MercadoLivreRestService mercadoLivreRestService;

	public void downloadPayment(String companyId) {
		List<QueuePayments> payments = queuePaymentsRepository.findByCompanyIdAndProcessStatus(companyId, 0);
		for (QueuePayments s : payments) {
			try {
				MercadoLivreNotificationDto notificDto = (MercadoLivreNotificationDto) s.getNotificationOriginalData();
				String token = getToken(s.getCompanyId(), notificDto.getUser_id());

				Object payment = mercadoLivreRestService.getPayment(token, s.getDocumentId());
				
				s.setDocumentOriginalData(payment);
				s.setProcessStatus(10);
				s.setUpdateDate(new Date());
				s.setUpdateId("DRAFT_PAYMENT_PROCESS");
				
				queuePaymentsRepository.save(s);
				
			} catch (Exception e) {
				log.error("Error to download payment" , e);
			}
		}
	}


}
