package br.silveira.conciliador.feignClient.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.silveira.conciliador.feignClient.dto.MercadoLivreShipmentCostDto;


@FeignClient(name = "mktplace-integrator")
public interface MercadoLivreResource {

	@GetMapping("/mercado-livre/getShippingCost/{companyId}/{mktPlaceUserId}/{itemId}/{zipcode}/{shippingMethodId}/{quantity}")
	public MercadoLivreShipmentCostDto getShippingCost(@PathVariable String companyId, @PathVariable Long mktPlaceUserId, @PathVariable String itemId, @PathVariable String zipcode, @PathVariable Double quantity, @PathVariable Integer shippingMethodId);

}
