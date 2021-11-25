package br.silveira.conciliador.integrator.mapper;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;

import br.silveira.conciliador.integrator.dto.MktPlaceIntegrationConfigDto;
import br.silveira.conciliador.integrator.entity.MktPlaceIntegrationConfig;

public class MktPlaceIntegrationConfigMapper {

	private static ModelMapper mapper = new ModelMapper();
	
	public static Map<String, String> mapperToTokenFormMercadoLivre(MktPlaceIntegrationConfigDto dto) {
		Map<String, String> tokenForm = new HashMap<String, String>();
		tokenForm.put("grant_type", "authorization_code");
		tokenForm.put("client_id", dto.getApplicationId());
		tokenForm.put("client_secret", dto.getClientSecret());
		tokenForm.put("code", dto.getAuthorizationCode());
		tokenForm.put("redirect_uri", dto.getRedirectUrl()); //TODO REMOVER DEPOIS
		return tokenForm;
	}

	public static MktPlaceIntegrationConfig mapperToEntity(MktPlaceIntegrationConfigDto dto) {
		return mapper.map(dto, MktPlaceIntegrationConfig.class);
	}

	public static MktPlaceIntegrationConfigDto mapperToDto(MktPlaceIntegrationConfig entity) {
		return mapper.map(entity, MktPlaceIntegrationConfigDto.class);
	}

}
