package br.silveira.conciliador.integrator.ctr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.common.constant.RestTagConstant;
import br.silveira.conciliador.integrator.dto.MktPlaceIntegrationConfigDto;
import br.silveira.conciliador.integrator.service.MktPlaceIntegrationConfigService;
import br.silveira.conciliador.integrator.service.QueueOrderService;

@RestController
@RequestMapping("/api/integrator")
@CrossOrigin(origins = "*")
public class IntegratorController {
	
	private static final Logger log = LogManager.getLogger(IntegratorController.class);

	@Autowired
	private QueueOrderService queueOrderService;
	
	@Autowired
	private MktPlaceIntegrationConfigService mktPlaceIntegrationConfigService;

	@PostMapping("/processOrder/{queueOrderId}")
	public ResponseEntity<Void> processOrder(@PathVariable(value="queueOrderId") String queueOrderId) {
		try {
			queueOrderService.processQueueOrder(queueOrderId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Error to save Queue Order Id: " + queueOrderId, e);
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}
	
	@PostMapping("/processAllOrder/{companyId}")
	public ResponseEntity<Void> processAllOrder(@PathVariable(value="companyId") String companyId) {
		try {
			queueOrderService.processAllQueueOrder(companyId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Error to save Orders - Company Id: " + companyId, e);
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}
	
	@PostMapping("/createMktPlaceIntegrationConfig")
	public ResponseEntity<Void> createMktPlaceIntegrationConfig(@RequestBody MktPlaceIntegrationConfigDto dto){
		try {
			mktPlaceIntegrationConfigService.save(dto);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

}