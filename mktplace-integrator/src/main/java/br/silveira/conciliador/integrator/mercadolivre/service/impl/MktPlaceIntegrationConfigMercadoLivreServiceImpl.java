package br.silveira.conciliador.integrator.mercadolivre.service.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.integrator.dto.MktPlaceIntegrationConfigDto;
import br.silveira.conciliador.integrator.entity.MktPlaceIntegrationConfig;
import br.silveira.conciliador.integrator.mapper.MktPlaceIntegrationConfigMapper;
import br.silveira.conciliador.integrator.mercadolivre.config.MercadoLivreConfig;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreTokenDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreUserInfoDto;
import br.silveira.conciliador.integrator.mercadolivre.service.MercadoLivreRestService;
import br.silveira.conciliador.integrator.repository.MktPlaceIntegrationConfigRepository;
import br.silveira.conciliador.integrator.service.MktPlaceIntegrationConfigService;

@Service("mktPlaceIntegrationConfigMercadoLivreServiceImpl")
public class MktPlaceIntegrationConfigMercadoLivreServiceImpl implements MktPlaceIntegrationConfigService {
	
	private static final Logger log = LogManager.getLogger(MktPlaceIntegrationConfigMercadoLivreServiceImpl.class);
	
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
	public boolean save(MktPlaceIntegrationConfigDto dto) {
		if(isMktPlaceIntegrationConfigDtoValid(dto)) {
			dto = checkConfigAlreadyExistByUserId(dto);

			dto.setMarketPlace(MktPlaceEnum.MERCADO_LIVRE);
			dto.setApplicationId(mercadoLivreConfig.APP_ID);
			dto.setClientSecret(mercadoLivreConfig.CLIENT_SECRET);
			dto.setRedirectUrl(mercadoLivreConfig.REDIRECT_URL);
			
			dto = getDataFromMercadoLivreApi(dto);
			
			repository.save(MktPlaceIntegrationConfigMapper.mapperToEntity(dto));
			log.info(String.format(AUTH_CODE_UPDATE_SUCCESSFULLY, dto.getCompanyId(), dto.getMarketPlace(), dto.getMktPlaceUserId()));
			return true;
			
		}else {
			log.error("Invalid DTO\nRule: [dto != null && dto.getAuthorizationCode() != null] - DTO: "+dto);
		}
		
		return false;
	}



	private MktPlaceIntegrationConfigDto getDataFromMercadoLivreApi(MktPlaceIntegrationConfigDto dto) {
		MercadoLivreTokenDto token = mercadoLivreRestService.getToken(MktPlaceIntegrationConfigMapper.mapperToTokenFormMercadoLivre(dto));
		MercadoLivreUserInfoDto userInfo = mercadoLivreRestService.getUserInfo(token.access_token, token.getUser_id());	
		dto.setMktPlaceUserId(""+token.getUser_id());		
		dto.setMktPlaceUsername(userInfo.getNickname());			
		dto.setApiToken(token.getAccess_token());
		dto.setApiRefreshToken(token.getRefresh_token());
		dto.setLastApiTokenUpdated(new Date());
		dto.setExpiresIn(token.getExpires_in());
		return dto;
	}
	
	

	private boolean isMktPlaceIntegrationConfigDtoValid(MktPlaceIntegrationConfigDto dto) {
		return dto != null && dto.getAuthorizationCode() != null ;
	}



	@Deprecated
	public void createMktPlaceIntegratioinConfigForMercadoLivre(MktPlaceIntegrationConfigDto dto) throws Exception {

		checkDtoForAuthorizationCodeUpdate(dto);
		
		if(checkConfigIfAlreadyExistOrIsDisable(dto)) {		
			dto.setApplicationId(mercadoLivreConfig.APP_ID);
			dto.setClientSecret(mercadoLivreConfig.CLIENT_SECRET);
			dto.setRedirectUrl(mercadoLivreConfig.REDIRECT_URL);
				
			MercadoLivreTokenDto token = mercadoLivreRestService.getToken(MktPlaceIntegrationConfigMapper.mapperToTokenFormMercadoLivre(dto));
			dto.setMktPlaceUserId(""+token.getUser_id());
			
			dto = checkConfigAlreadyExistByUserId(dto);
			
			dto.setApiToken(token.getAccess_token());
			dto.setApiRefreshToken(token.getRefresh_token());
			dto.setLastApiTokenUpdated(new Date());
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
			dto.setUpdateDate(new Date());
			dto.setUpdateId( StringUtils.hasText(dto.getInsertId()) ? dto.getInsertId(): MERCADO_LIVRE_AUTHORIZATION_REDIRECT);
			return mapperToDto;
		}else {
			dto.setInsertDate(new Date());
			dto.setInsertId(StringUtils.hasText(dto.getInsertId()) ? dto.getInsertId(): MERCADO_LIVRE_AUTHORIZATION_REDIRECT);
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

}
