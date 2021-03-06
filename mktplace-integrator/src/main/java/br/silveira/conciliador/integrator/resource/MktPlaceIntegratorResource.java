package br.silveira.conciliador.integrator.resource;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.common.constant.RestTagConstant;
import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.integrator.dto.MktPlaceIntegrationConfigDto;
import br.silveira.conciliador.integrator.entity.MktPlaceIntegrationConfig;
import br.silveira.conciliador.integrator.factories.MktPlaceIntegrationConfigFactory;
import br.silveira.conciliador.integrator.repository.MktPlaceIntegrationConfigRepository;
import br.silveira.conciliador.integrator.service.QueueOrderService;

@RestController
@RequestMapping("/mktplace-integrator")
//@CrossOrigin(origins = "*")
public class MktPlaceIntegratorResource {

	private static final Logger log = LogManager.getLogger(MktPlaceIntegratorResource.class);

	@Autowired
	private QueueOrderService queueOrderService;

	@Autowired
	private MktPlaceIntegrationConfigRepository mktPlaceIntegrationConfigRepository;

	@Autowired
	private MktPlaceIntegrationConfigFactory mktPlaceIntegrationConfigFactory;

	@PostMapping("/processOrder/{queueOrderId}")
	public ResponseEntity<Void> processOrder(@PathVariable(value = "queueOrderId") String queueOrderId) {
		try {
			queueOrderService.processQueueOrder(queueOrderId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Error to save Queue Order Id: " + queueOrderId, e);
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@PostMapping("/processAllOrder/{companyId}")
	public ResponseEntity<Void> processAllOrder(@PathVariable(value = "companyId") String companyId) {
		try {
			queueOrderService.processAllQueueOrder(companyId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Error to save Orders - Company Id: " + companyId, e);
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}
	
	
	@PostMapping("/downloadOrder/{queueOrderId}")
	public ResponseEntity<Void> downloadOrder(@PathVariable(value = "queueOrderId") String queueOrderId) {
		try {
			queueOrderService.downloadQueueOrder(queueOrderId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Error to download Queue Order Id: " + queueOrderId, e);
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@PostMapping("/downloadAllOrder/{companyId}")
	public ResponseEntity<Void> downloadAllOrder(@PathVariable(value = "companyId") String companyId) {
		try {
			queueOrderService.downloadAllQueueOrder(companyId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("Error to download Orders - Company Id: " + companyId, e);
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@PostMapping("/createMktPlaceIntegrationConfigMercadoLivre")
	public ResponseEntity<Void> createMktPlaceIntegrationConfigMercadoLivre(@RequestBody MktPlaceIntegrationConfigDto dto) {
		try {
			if (mktPlaceIntegrationConfigFactory.getImpl(MktPlaceEnum.MERCADO_LIVRE).save(dto)) {
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.badRequest().build();
			}
		} catch (Exception e) {
			log.error("Error to createMktPlaceIntegrationConfigMercadoLivre - DTO: " + dto, e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/getMktPlaceIntegrationConfigUsers/{companyId}/{mktPlace}")
	public ResponseEntity<List<MktPlaceIntegrationConfig>> getMktPlaceIntegrationConfigUsers(@PathVariable String companyId, @PathVariable String mktPlace) {
		try {
			List<MktPlaceIntegrationConfig> retEntities = mktPlaceIntegrationConfigRepository.findMktPlaceUsersByCompanyIdAndMarketPlaceOrderByMktPlaceUsername(companyId, MktPlaceEnum.valueOf(mktPlace.toUpperCase()));
			if (retEntities == null || retEntities.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(retEntities);
			}
		} catch (Exception e) {
			log.error("Error to getMktPlaceIntegrationConfigUsers - Company ID: " + companyId + "| Mkt Place: "+mktPlace, e);
			return ResponseEntity.internalServerError().build();
		}
	}

}
