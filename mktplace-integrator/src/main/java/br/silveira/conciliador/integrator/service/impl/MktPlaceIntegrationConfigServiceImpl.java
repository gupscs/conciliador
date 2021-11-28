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
import br.silveira.conciliador.integrator.mercadolivre.config.MercadoLivreConfig;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreTokenDto;
import br.silveira.conciliador.integrator.mercadolivre.service.MercadoLivreRestService;
import br.silveira.conciliador.integrator.repository.MktPlaceIntegrationConfigRepository;
import br.silveira.conciliador.integrator.service.MktPlaceIntegrationConfigService;

@Service
public class MktPlaceIntegrationConfigServiceImpl extends CommonServiceImpl implements MktPlaceIntegrationConfigService {
	
	private static final Logger log = LogManager.getLogger(MktPlaceIntegrationConfigServiceImpl.class);
	
	private static final String MKT_PLACE_INTEG_DISABLE = "Market Place Integration is disable for Company Id: %s and Market Place: %s  and User Id: %s - Check with System Administrator";
	private static final String MKT_PLACE_USER_ALREADY_REGISTERED = "User Id: %s is already registered for Company Id: %s and Market Place: %s";
	private static final String AUTH_CODE_UPDATE_SUCCESSFULLY = "Authorization Code and First Token sucessfully registered for Company Id: %s and Mkt Place: %s and User Id: %s";
	private static final String MERCADO_LIVRE_AUTHORIZATION_REDIRECT = "MERCADO_LIVRE_AUTHORIZATION_REDIRECT";
	
	@Autowired
	private MktPlaceIntegrationConfigRepository repository;
	
	@Autowired
	private MercadoLivreRestService  mercadoLivreRestService;
	
	@Autowired
	private MercadoLivreConfig mercadoLivreConfig;

	@Override
	public void createMktPlaceIntegratioinConfigForMercadoLivre(MktPlaceIntegrationConfigDto dto) throws Exception {

		checkDtoForAuthorizationCodeUpdate(dto);
		dto.setCompanyId(getCompanyId());
		
		if(checkConfigIfAlreadyExistOrIsDisable(dto)) {		
			dto.setApplicationId(mercadoLivreConfig.APP_ID);
			dto.setClientSecret(mercadoLivreConfig.CLIENT_SECRET);
			dto.setRedirectUrl(mercadoLivreConfig.REDIRECT_URL);
				
			MercadoLivreTokenDto token = mercadoLivreRestService.getToken(MktPlaceIntegrationConfigMapper.mapperToTokenFormMercadoLivre(dto));
			dto.setMktPlaceUserId(""+token.getUser_id());
			
			dto = checkConfigAlreadyExistByUserId(dto);
			
			dto.setApiToken(token.getAccess_token());
			dto.setApiRefreshToken(token.getRefresh_token());
			dto.setLastApiTokenUpdated(LocalDateTime.now());
			dto.setExpiresIn(token.getExpires_in());
			
			repository.save(MktPlaceIntegrationConfigMapper.mapperToEntity(dto));
			
			log.info(String.format(AUTH_CODE_UPDATE_SUCCESSFULLY, dto.getCompanyId(), dto.getMarketPlace(), dto.getMktPlaceUserId()));
		}
		
	}

	private MktPlaceIntegrationConfigDto checkConfigAlreadyExistByUserId(MktPlaceIntegrationConfigDto dto) {
		Optional<MktPlaceIntegrationConfig> mktConfigEntity = repository.findByCompanyIdAndMktPlaceAndMktPlaceUserId(dto.getCompanyId(), dto.getMarketPlace(), dto.getMktPlaceUserId());
		if(mktConfigEntity.isPresent()) {
			MktPlaceIntegrationConfigDto mapperToDto = MktPlaceIntegrationConfigMapper.mapperToDto(mktConfigEntity.get());
			mapperToDto.setAuthorizationCode(dto.getAuthorizationCode());
			dto.setUpdateDate(LocalDateTime.now());
			dto.setUpdateId(MERCADO_LIVRE_AUTHORIZATION_REDIRECT);
			return mapperToDto;
		}else {
			dto.setInsertDate(LocalDateTime.now());
			dto.setInsertId(MERCADO_LIVRE_AUTHORIZATION_REDIRECT);
			return dto;
		}
	}

	private Boolean checkConfigIfAlreadyExistOrIsDisable(MktPlaceIntegrationConfigDto dto) throws Exception {
		Optional<MktPlaceIntegrationConfig> mktPlaceInteg = repository.findByCompanyIdAndMktPlaceAndAuthorizationCode(dto.getCompanyId(), dto.getMarketPlace(), dto.getAuthorizationCode());
		if(mktPlaceInteg.isPresent()) {
			if(!mktPlaceInteg.get().getEnable()) {
				throw new Exception(String.format(MKT_PLACE_INTEG_DISABLE, dto.getCompanyId(), dto.getMarketPlace(), mktPlaceInteg.get().getMktPlaceUserId()));
			}else {
				log.warn(String.format(MKT_PLACE_USER_ALREADY_REGISTERED, mktPlaceInteg.get().getMktPlaceUserId(), dto.getCompanyId(), dto.getMarketPlace()));
				return false;
			}
		}
		return true;
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
