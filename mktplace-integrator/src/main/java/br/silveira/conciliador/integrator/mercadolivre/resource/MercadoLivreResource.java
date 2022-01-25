package br.silveira.conciliador.integrator.mercadolivre.resource;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.integrator.dto.MktPlaceIntegrationConfigDto;
import br.silveira.conciliador.integrator.dto.QueueDto;
import br.silveira.conciliador.integrator.mapper.QueueDtoMapper;
import br.silveira.conciliador.integrator.mercadolivre.config.MercadoLivreConfig;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreNotificationDto;
import br.silveira.conciliador.integrator.service.MktPlaceIntegrationConfigService;
import br.silveira.conciliador.integrator.service.QueueFeedbackService;
import br.silveira.conciliador.integrator.service.QueueNotImplementedService;
import br.silveira.conciliador.integrator.service.QueueOrderService;
import br.silveira.conciliador.integrator.service.QueuePaymentsService;
import br.silveira.conciliador.integrator.service.QueueShipmentsService;

@RestController
@RequestMapping("/mktplace-integrator/mercado-livre")
@CrossOrigin(origins = "*")	
public class MercadoLivreResource {

	private static final Logger log = LogManager.getLogger(MercadoLivreResource.class);
	
	@Autowired
	private MercadoLivreConfig config;
	
	@Autowired
	private QueueOrderService queueOrderService;
	
	@Autowired
	private QueueShipmentsService queueShipmentsService;
	
	@Autowired
	private QueuePaymentsService queuePaymentsService;
	
	@Autowired
	private QueueFeedbackService queueFeedbackService;
	
	@Autowired
	private QueueNotImplementedService queueNotImplementedService;
	
	@Autowired
	@Qualifier("mktPlaceIntegrationConfigMercadoLivreServiceImpl")
	private MktPlaceIntegrationConfigService mktPlaceIntegrationConfigService;

	//https://developers.mercadolivre.com.br/pt_br/produto-receba-notificacoes?nocache=true#Teste-suas-notificacoes
	
	@PostMapping("/notification")
	public ResponseEntity<?> notification(@RequestBody MercadoLivreNotificationDto notificationDto) {
		QueueDto dto = QueueDtoMapper.mapperFromMercadoLivreNotification(notificationDto);
		try {
			if(config.TOPIC_CREATED_ORDERS.equals(notificationDto.getTopic()) || config.TOPIC_ORDERS.equals(notificationDto.getTopic())) {
				queueOrderService.saveQueueOrders(dto);
			}else if(config.TOPIC_SHIPMENTS.equals(notificationDto.getTopic())) {
				queueShipmentsService.saveQueueShipments(dto);
			}else if(config.TOPIC_PAYMENTS.equals(notificationDto.getTopic())) {
				queuePaymentsService.saveQueuePayments(dto);
			}else if(config.TOPIC_FEEDBACK.equals(notificationDto.getTopic())) {
				queueFeedbackService.saveQueueFeedbacks(dto);
			}else {
				queueNotImplementedService.saveQueueNotImplemented(dto);
			}
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Mercado Livre Notification receive error - Notification DTO: "+dto, e);
			return ResponseEntity.badRequest().build();
		}

	}
	
	@GetMapping("/token-redirect")
	public ResponseEntity<String> tokenRedirect(@RequestParam(name = "code", required = false) String authCode, @RequestParam(name = "error", required = false) String error, @RequestParam(name = "error_description", required = false) String errorDescription) {
		if(StringUtils.hasLength(error)) {
			log.error("Authorization Code from Mercado Livre error - Error: "+error+" - Error Description: "+errorDescription);
			return ResponseEntity.badRequest().body(new String("Error: "+error+" - Error Description: "+errorDescription));
		}else {
			return ResponseEntity.ok().build();
		}
	}
	
	@GetMapping("/authorization")
	public ResponseEntity<String> authorization(@RequestParam(name = "code", required = false) String authCode, @RequestParam(name = "error", required = false) String error, @RequestParam(name = "error_description", required = false) String errorDescription) {
		if(StringUtils.hasLength(error)) {
			log.error("Authorization Code from Mercado Livre error - Error: "+error+" - Error Description: "+errorDescription);
			return ResponseEntity.badRequest().body(new String("Error: "+error+" - Error Description: "+errorDescription));
		}
		
		try {
			MktPlaceIntegrationConfigDto dto = new MktPlaceIntegrationConfigDto();
			dto.setAuthorizationCode(authCode);
			dto.setMarketPlace(MktPlaceEnum.MERCADO_LIVRE);	
			mktPlaceIntegrationConfigService.createMktPlaceIntegratioinConfigForMercadoLivre(dto);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			log.error("Authorization Code from Mercado Livre error", e);
			return ResponseEntity.badRequest().body(new String(e.getMessage()));
			
		}
	}
	
	@GetMapping("/getAppIdAndRedirectUrl")
	public ResponseEntity<Map<String, String>> getAppId(){
		Map<String, String> ret = new HashMap<String , String>();
		ret.put("appId", config.APP_ID);
		ret.put("redirectUrl", config.REDIRECT_URL);
		return ResponseEntity.ok(ret);
	}

}
