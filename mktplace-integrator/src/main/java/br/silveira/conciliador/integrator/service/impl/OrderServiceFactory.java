package br.silveira.conciliador.integrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.integrator.service.OrderService;

@Component
public class OrderServiceFactory {
	
	@Autowired
	@Qualifier("orderMercadoLivreServiceImpl")
	private OrderService orderMercadoLivreService;
	
	public OrderService getImpl(MktPlaceEnum marketPlace) {
		switch (marketPlace) {
		case MERCADO_LIVRE:
			return orderMercadoLivreService;
		case SHOPEE:
			return null;
		default:
			return null;
		}
	}
	
	
	
}
