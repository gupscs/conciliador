package br.silveira.conciliador.costcalc.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.costcalc.service.MktPlaceCostCalculationService;

@Component
public class MktPlaceCostCalculationFactory {

	@Autowired
	@Qualifier("mktPlaceCostCalculationMercadoLivreServiceImpl")
	private MktPlaceCostCalculationService mktPlaceCostCalculationMercadoLivreService;

	public MktPlaceCostCalculationService getImpl(MktPlaceEnum marketPlace) {
		switch (marketPlace) {
		case MERCADO_LIVRE:
			return mktPlaceCostCalculationMercadoLivreService;
		case SHOPEE:
			return null;
		default:
			return null;
		}
	}

}
