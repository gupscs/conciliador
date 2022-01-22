package br.silveira.conciliador.integrator.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.integrator.service.MktPlaceIntegrationConfigService;

@Component
public class MktPlaceIntegrationConfigFactory {

	@Autowired
	@Qualifier("mktPlaceIntegrationConfigMercadoLivreServiceImpl")
	private MktPlaceIntegrationConfigService mktPlaceIntegrationConfigMercadoLivreService;

	public MktPlaceIntegrationConfigService getImpl(MktPlaceEnum marketPlace) {
		switch (marketPlace) {
		case MERCADO_LIVRE:
			return mktPlaceIntegrationConfigMercadoLivreService;
		case SHOPEE:
			return null;
		default:
			return null;
		}
	}

}
