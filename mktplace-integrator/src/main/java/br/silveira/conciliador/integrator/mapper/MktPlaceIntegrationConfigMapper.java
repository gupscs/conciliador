package br.silveira.conciliador.integrator.mapper;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;

import br.silveira.conciliador.integrator.dto.MktPlaceIntegrationConfigDto;
import br.silveira.conciliador.integrator.entity.MktPlaceIntegrationConfig;

public class MktPlaceIntegrationConfigMapper {

	private static ModelMapper mapper = new ModelMapper();
	
	public static Map<String, String> mapperToTokenFormMercadoLivre(MktPlaceIntegrationConfig entity) {
		Map<String, String> tokenForm = new HashMap<String, String>();
		tokenForm.put("grant_type", "authorization_code");
		tokenForm.put("client_id", entity.getApplicationId());
		tokenForm.put("client_secret", entity.getClientSecret());
		tokenForm.put("code", entity.getAuthorizationCode());
		return tokenForm;
	}

	public static MktPlaceIntegrationConfig mapperToEntity(MktPlaceIntegrationConfigDto dto) {
		return mapper.map(dto, MktPlaceIntegrationConfig.class);
	}

}
