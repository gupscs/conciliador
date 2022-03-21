package br.silveira.conciliador.organizational.resource;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.silveira.conciliador.common.constant.RestTagConstant;
import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.organizational.dto.CompanyCostValuesDto;
import br.silveira.conciliador.organizational.dto.CompanyCostValuesRequestDto;
import br.silveira.conciliador.organizational.dto.CompanyDto;
import br.silveira.conciliador.organizational.dto.FixedCostDto;
import br.silveira.conciliador.organizational.dto.ItemAverageCostDto;
import br.silveira.conciliador.organizational.dto.MktPlaceFeeDto;
import br.silveira.conciliador.organizational.dto.RegisterCheckDto;
import br.silveira.conciliador.organizational.dto.RegisterDto;
import br.silveira.conciliador.organizational.entity.Company;
import br.silveira.conciliador.organizational.entity.FixedCost;
import br.silveira.conciliador.organizational.entity.ItemAverageCost;
import br.silveira.conciliador.organizational.entity.MktPlaceFee;
import br.silveira.conciliador.organizational.repository.CompanyRepository;
import br.silveira.conciliador.organizational.repository.FixedCostRepository;
import br.silveira.conciliador.organizational.repository.ItemAverageCostRepository;
import br.silveira.conciliador.organizational.repository.MktPlaceFeeRepository;
import br.silveira.conciliador.organizational.service.CompanyService;
import br.silveira.conciliador.organizational.service.ItemAverageCostService;
import br.silveira.conciliador.organizational.service.MktPlaceFeeService;

@RestController
@RequestMapping("/organizational")
//@CrossOrigin(origins = "*")
public class OrganizationalResource {

	private static final Logger log = LogManager.getLogger(OrganizationalResource.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private FixedCostRepository fixedCostRepository;

	@Autowired
	private ItemAverageCostRepository itemAverageCostRepository;

	@Autowired
	private ItemAverageCostService itemAverageCostService;

	@Autowired
	private MktPlaceFeeRepository mktPlaceFeeRepository;
	
	@Autowired
	private MktPlaceFeeService mktPlaceFeeService;

	@PostMapping("/saveCompany")
	public ResponseEntity<Void> saveCompany(CompanyDto companyDto) {
		try {
			companyService.saveCompany(companyDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@GetMapping("/getCompanyCostValues")
	public ResponseEntity<CompanyCostValuesDto> getCompanyCostValues(CompanyCostValuesRequestDto dto) {
		try {
			CompanyCostValuesDto ret = companyService.getCompanyCostValues(dto);
			if (ret == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok(ret);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody RegisterDto register) {
		try {
			companyService.register(register);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@GetMapping("/registerCheck/{identificationNo}/{username}")
	public ResponseEntity<RegisterCheckDto> registerCheck(@PathVariable String identificationNo, @PathVariable String username) {
		try {
			RegisterCheckDto dto = companyService.registerCheck(identificationNo, username);
			return ResponseEntity.ok().body(dto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@GetMapping("/getCompanyInfo/{id}")
	public ResponseEntity<Company> getCompanyInfo(@PathVariable String id) {
		try {
			Optional<Company> companyInfo = companyRepository.findById(id);
			if (companyInfo.isPresent()) {
				return ResponseEntity.ok().body(companyInfo.get());
			} else {
				log.warn("No Content for Company ID: " + id + ", check the frontend app or user session");
				return ResponseEntity.noContent().build();
			}
		} catch (Exception e) {
			log.error("getCompanyInfo error, Company ID: " + id, e);
			return ResponseEntity.internalServerError().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@PostMapping("/updateCompanyInfo")
	public ResponseEntity<Void> updateCompanyInfo(@RequestBody CompanyDto companyDto) {
		try {
			if (companyService.updateCompanyInfo(companyDto)) {
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.badRequest().build();
			}
		} catch (Exception e) {
			log.error("Error to update the Company Info: DTO: " + companyDto, e);
			return ResponseEntity.internalServerError().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@GetMapping("/getFixedCostByCompanyId/{companyId}")
	public ResponseEntity<List<FixedCost>> getFixedCost(@PathVariable String companyId) {
		try {
			List<FixedCost> fixedCosts = fixedCostRepository.findByCompanyId(companyId);
			if (CollectionUtils.isEmpty(fixedCosts)) {
				log.warn("No Content for Company ID: " + companyId + ", check the frontend app or user session");
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok().body(fixedCosts);
			}
		} catch (Exception e) {
			log.error("getCompanyInfo error, Company ID: " + companyId, e);
			return ResponseEntity.internalServerError().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@PostMapping("/saveFixedCost")
	public ResponseEntity<FixedCostDto> saveFixedCost(@RequestBody FixedCostDto fixedCostDto) {
		try {
			FixedCostDto saveFixedCost = companyService.saveFixedCost(fixedCostDto);
			if (saveFixedCost == null) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(saveFixedCost);
			}
		} catch (Exception e) {
			log.error("saveFixedCost error, DTO: " + fixedCostDto, e);
			return ResponseEntity.internalServerError().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@DeleteMapping("/deleteFixedCost/{fixedCostId}")
	public ResponseEntity<Void> deleteFixedCost(@PathVariable String fixedCostId) {
		try {
			fixedCostRepository.deleteById(fixedCostId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("deleteFixedCost error, ID: " + fixedCostId, e);
			return ResponseEntity.internalServerError().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@GetMapping("/getItemAverageCost/{companyId}")
	public ResponseEntity<List<ItemAverageCost>> getItemAverageCost(@PathVariable String companyId) {
		try {
			List<ItemAverageCost> itemAverageCost = itemAverageCostRepository.findByCompanyId(companyId);
			if (CollectionUtils.isEmpty(itemAverageCost)) {
				log.warn("No Content for Company ID: " + companyId + ", check the frontend app or user session");
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok().body(itemAverageCost);
			}
		} catch (Exception e) {
			log.error("getItemAverageCost error, Company ID: " + companyId, e);
			return ResponseEntity.internalServerError().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@PostMapping("/saveItemAverageCost")
	public ResponseEntity<Void> saveItemAverageCost(@RequestBody ItemAverageCostDto itemAverageCostDto) {
		try {
			if (itemAverageCostService.saveItemAverageCost(itemAverageCostDto)) {
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.badRequest().build();
			}
		} catch (Exception e) {
			log.error("Error to saveItemAverageCost  the Company Info: DTO: " + itemAverageCostDto, e);
			return ResponseEntity.internalServerError().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@GetMapping("/getMktPlaceFee/{companyId}/{mktPlace}")
	public ResponseEntity<List<MktPlaceFee>> getMktPlaceFee(@PathVariable String companyId, @PathVariable MktPlaceEnum mktPlace) {
		try {
			List<MktPlaceFee> mktPlaceFee = mktPlaceFeeRepository.findByCompanyIdAndMarketPlace(companyId, mktPlace);
			if (CollectionUtils.isEmpty(mktPlaceFee)) {
				log.warn("No Content for Company ID: " + companyId + ", check the frontend app or user session");
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok().body(mktPlaceFee);
			}
		} catch (Exception e) {
			log.error("getMktPlaceFee error, Company ID: " + companyId + " MktPlaceEnum: " + mktPlace, e);
			return ResponseEntity.internalServerError().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

	@DeleteMapping("/deleteMktPlaceFee/{id}")
	public ResponseEntity<Void> deleteMktPlaceFee(@PathVariable String id) {
		try {
			mktPlaceFeeRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("deleteMktPlaceFee error,  ID: " + id, e);
			return ResponseEntity.internalServerError().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}
	
	
	@PostMapping("/saveMktPlaceFee")
	public ResponseEntity<Void> saveMktPlaceFee(@RequestBody MktPlaceFeeDto mktPlaceFeeDto) {
		try {
			if (mktPlaceFeeService.saveMktPlaceFee(mktPlaceFeeDto)) {
				return ResponseEntity.ok().build();
			} else {
				log.warn("No saved: "+mktPlaceFeeDto);
				return ResponseEntity.badRequest().build();
			}
		} catch (Exception e) {
			log.error("saveMktPlaceFee error, MktPlaceFeeDto: "+mktPlaceFeeDto, e);
			return ResponseEntity.internalServerError().header(RestTagConstant.HD_ERROR_MSG_TAG, e.getMessage()).build();
		}
	}

}
