package br.silveira.conciliador.integrator.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.silveira.conciliador.integrator.dto.MktPlaceIntegrationConfigDto;
import br.silveira.conciliador.integrator.entity.MktPlaceIntegrationConfig;
import br.silveira.conciliador.integrator.mapper.MktPlaceIntegrationConfigMapper;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreTokenDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreUserInfoDto;
import br.silveira.conciliador.integrator.mercadolivre.service.MercadoLivreRestService;
import br.silveira.conciliador.integrator.repository.MktPlaceIntegrationConfigRepository;
import br.silveira.conciliador.integrator.service.MktPlaceIntegrationConfigService;

@Service
public class MktPlaceIntegrationConfigServiceImpl extends CommonServiceImpl implements MktPlaceIntegrationConfigService {
	
	private static final Logger log = LogManager.getLogger(MktPlaceIntegrationConfigServiceImpl.class);
	
	private static final String BEARER = "Bearer ";
	
	private static final String MKT_PLACE_INTEG_DISABLE = "Market Place Integration is disable for Company Id: %s and Market Place: %s - Check with System Administrator";
	private static final String MKT_PLACE_INTEG_NOT_EXIST = "Market Place Integration not exist for Company Id: %s and Market Place: %s - Check with System Administrator";
	private static final String AUTH_CODE_UPDATE_SUCCESSFULLY = "Authorization Code and First Token sucessfully updated for Company Id: %s and Mkt Place: %s";
	
	@Autowired
	private MktPlaceIntegrationConfigRepository repository;
	
	@Autowired
	private MercadoLivreRestService  mercadoLivreRestService;

	@Override
	public void createMktPlaceIntegratioinConfigForMercadoLivre(MktPlaceIntegrationConfigDto dto) throws Exception {
		checkDtoForAuthorizationCodeUpdate(dto);
		Optional<MktPlaceIntegrationConfig> mktPlaceInteg = repository.findByCompanyIdAndMktPlace(dto.getCompanyId(), dto.getMarketPlace());
		if(mktPlaceInteg.isPresent()) {
			if(!mktPlaceInteg.get().getEnable()) {
				throw new Exception(String.format(MKT_PLACE_INTEG_DISABLE, dto.getCompanyId(), dto.getMarketPlace()));
			}
			
			MktPlaceIntegrationConfig entity = mktPlaceInteg.get();
			entity.setAuthorizationCode(dto.getAuthorizationCode());
			entity.setUpdateDate(LocalDateTime.now());
			entity.setUpdateId(dto.getUpdateId());
			
			MercadoLivreTokenDto token = mercadoLivreRestService.getToken(MktPlaceIntegrationConfigMapper.mapperToTokenFormMercadoLivre(entity));
			MercadoLivreUserInfoDto userInfo = mercadoLivreRestService.getUserInfo(BEARER+token.getAccess_token());
			
			entity.setMktPlaceUserId(""+userInfo.getUser_id());
			entity.setApiToken(token.getAccess_token());
			entity.setApiRefreshToken(token.getRefresh_token());
			entity.setLastApiTokenUpdated(LocalDateTime.now());
			entity.setExpiresIn(token.getExpires_in());
			entity.setUpdateDate(LocalDateTime.now());
			entity.setUpdateId("AUTO");
			repository.save(entity);
			log.info(String.format(AUTH_CODE_UPDATE_SUCCESSFULLY, dto.getCompanyId(), dto.getMarketPlace()));
			
		}else {
			throw new Exception(String.format(MKT_PLACE_INTEG_NOT_EXIST, dto.getCompanyId(), dto.getMarketPlace()));
		}
	}

	private void checkDtoForAuthorizationCodeUpdate(MktPlaceIntegrationConfigDto dto) throws Exception {
		StringBuilder error = new StringBuilder();
		
		if(dto == null) {
			throw new NullPointerException("MktPlaceIntegrationConfigDto dto is null");
		}
		
		if(dto.getMarketPlace() == null) {
			error.append("|MarketPlace Id is required"); 
		}
		
		if(!StringUtils.hasLength(dto.getAuthorizationCode())) {
			error.append("|Authorization Code is required"); 
		}
		
		String ret = error.toString();
		if(StringUtils.hasLength(ret)) {
			throw new Exception(ret);
		}
		
	}

	@Override
	public void save(MktPlaceIntegrationConfigDto dto) {
		String id = repository.save(MktPlaceIntegrationConfigMapper.mapperToEntity(dto)).getId();
		log.info("MktPlaceIntegrationConfig save successfully - ID: "+id);
	}
}
