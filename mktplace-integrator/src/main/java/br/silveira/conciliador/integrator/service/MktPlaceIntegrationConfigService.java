package br.silveira.conciliador.integrator.service;

import br.silveira.conciliador.integrator.dto.MktPlaceIntegrationConfigDto;

public interface MktPlaceIntegrationConfigService {
	
	
	public void createMktPlaceIntegratioinConfigForMercadoLivre(MktPlaceIntegrationConfigDto dto) throws Exception;

	public void save(MktPlaceIntegrationConfigDto dto);

}
