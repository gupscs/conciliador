package br.silveira.conciliador.integrator.mercadolivre.service;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.integrator.entity.QueueShipments;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreShipmentCostDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreShipmentDto;

public interface ShipmentMercadoLivreService {

	public void downloadAllShipmentsByCompanyId(String companyId);

	public MercadoLivreShipmentCostDto getShippingCostByItemCode(String companyId, Long mktPlaceUserId, String itemId, String zipcode, Double quantity, Integer shippingMethodId) throws Exception;

	public MercadoLivreShipmentDto getShipmentByCompanyIdAndMktPlaceAndShipmentId(String companyId, MktPlaceEnum mktPlace, String shipmentId) throws Exception;

	public QueueShipments downloadShipmentByQueueShipments(QueueShipments queueShipments) throws Exception;

}
