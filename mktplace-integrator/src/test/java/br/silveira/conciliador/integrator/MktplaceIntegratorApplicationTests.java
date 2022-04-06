package br.silveira.conciliador.integrator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.silveira.conciliador.integrator.mercadolivre.service.impl.PaymentMercadoLivreServiceImpl;
import br.silveira.conciliador.integrator.mercadolivre.service.impl.ShipmentMercadoLivreServiceImpl;
import br.silveira.conciliador.integrator.resource.MktPlaceIntegratorResource;
import br.silveira.conciliador.integrator.service.QueueOrderService;

@SpringBootTest
class MktplaceIntegratorApplicationTests {

	private static final String COMPANY_ID =  "6228f0e547f8b1139038a97a";

	
	@Autowired
	private QueueOrderService queueOrderService;
	
	@Autowired
	private ShipmentMercadoLivreServiceImpl shipmentMercadoLivreServiceImpl;
	
	@Autowired
	private PaymentMercadoLivreServiceImpl paymentMercadoLivreServiceImpl; 
	
	
	@Autowired
	private MktPlaceIntegratorResource mktplaceIntegratorResource;
	
	@Test
	void processOrderId() {
		
		mktplaceIntegratorResource.processOrder("6229407f5d36f9543e6e1b6b");
	}
	
	
	//@Test
	void contextLoads() {
	}
	
	//@Test
	void testQueueOrderProcess() {
		try {
			queueOrderService.processQueueOrder("61a6c527117ab034ba5ef0d3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//@Test
	void testQueueOrderDownload() {
		try {
			//queueOrderService.downloadQueueOrder("62294dcf56e27043ffaae219");
			queueOrderService.downloadAllQueueOrder(COMPANY_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	void testQueueShipmentDownload() {
		try {
			//shipmentMercadoLivreServiceImpl.downloadShipment(COMPANY_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testQueuePaymentDownload() {
		try {
			paymentMercadoLivreServiceImpl.downloadPayment(COMPANY_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
