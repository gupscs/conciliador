package br.silveira.conciliador.integrator.mercadolivre.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.common.util.DateUtil;
import br.silveira.conciliador.integrator.entity.MktPlaceIntegrationConfig;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreTokenDto;
import br.silveira.conciliador.integrator.mercadolivre.service.MercadoLivreRestService;
import br.silveira.conciliador.integrator.repository.MktPlaceIntegrationConfigRepository;

public class MercadoLivreServiceCommon {
	
	private static final String  REFRESH_TOKEN = "refresh_token";
	
	@Autowired
	MktPlaceIntegrationConfigRepository mktPlaceIntegrationConfigRepository;
	

	@Autowired
	MercadoLivreRestService mercadoLivreService;
	
	
	public String getToken(String companyId, Long mktPlaceUserId) throws Exception {
		Optional<MktPlaceIntegrationConfig> mktPlaceInteg = mktPlaceIntegrationConfigRepository.findByCompanyIdAndMktPlaceAndMktPlaceUserId(companyId, MktPlaceEnum.MERCADO_LIVRE, ""+mktPlaceUserId);
		if(!mktPlaceInteg.isPresent()) throw new Exception("Missing Mkt Place Integration Config for Mercado Livre : Company Id: "+companyId);
		
		MktPlaceIntegrationConfig entity = mktPlaceInteg.get();
		String apiToken = entity.getApiToken();
		String apiRefreshToken = entity.getApiRefreshToken();
		Date lastUpdate = entity.getLastApiTokenUpdated();
		Integer expireIn = entity.getExpiresIn();
		String clientId = entity.getApplicationId();
		String clientSecret = entity.getClientSecret();
		if(DateUtil.isExpiredToken(lastUpdate, expireIn)) {
			MercadoLivreTokenDto token = mercadoLivreService.refreshToken(REFRESH_TOKEN, clientId, clientSecret, apiRefreshToken);
			entity.setApiToken(token.getAccess_token());
			entity.setApiRefreshToken(token.getRefresh_token());
			entity.setLastApiTokenUpdated(new Date());
			entity.setExpiresIn(token.getExpires_in());
			entity.setUpdateDate(new Date());
			entity.setUpdateId("AUTO");
			mktPlaceIntegrationConfigRepository.save(entity);
			apiToken = token.getAccess_token();
		}
		
		return apiToken;
	}

}
