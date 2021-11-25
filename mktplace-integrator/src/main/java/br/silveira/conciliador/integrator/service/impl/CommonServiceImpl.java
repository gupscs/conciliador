package br.silveira.conciliador.integrator.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.integrator.dto.QueueDto;
import br.silveira.conciliador.integrator.entity.MktPlaceIntegrationConfig;
import br.silveira.conciliador.integrator.repository.MktPlaceIntegrationConfigRepository;

public class CommonServiceImpl {
	
	
	static final Integer ERROR_PROCESS_STATUS = 99;
	
	static final String ERROR_MISSING_CONFIG = "MktPlaceIntegrationConfig is missing or disable for Mkt Place: %s - User Id: %s";
	
	@Autowired
	MktPlaceIntegrationConfigRepository mktPlaceIntegrationConfigRepository;

	
	public QueueDto updateQueueDtoBasedMarketPlace(QueueDto dto) {
		if(StringUtils.hasLength(dto.getCompanyId())) {
			return dto;
		}
		if(dto.getMarketPlace().equals(MktPlaceEnum.MERCADO_LIVRE)) {
			Optional<MktPlaceIntegrationConfig> config = mktPlaceIntegrationConfigRepository.findByCompanyIdAndMktPlaceAndMktPlaceUserId(dto.getCompanyId(), dto.getMarketPlace(), dto.getMktPlaceUserId());
			if(config.isPresent() && config.get().getEnable()) {
				dto.setCompanyId(config.get().getCompanyId());
			}else {
				dto.setProcessStatus(ERROR_PROCESS_STATUS);
				dto.setProcessMsg(String.format(ERROR_MISSING_CONFIG,dto.getMarketPlace(),dto.getMktPlaceUserId()));
			}
		}
		return dto;
	}
	
	public String getCompanyId() {
		//TODO IMPLEMENTAR LEITURA DO COMPANY ID A PARTIR DO JWT TOKEN / SPRING SECURITY CONTEXT
		return "2";
	}
	
}
