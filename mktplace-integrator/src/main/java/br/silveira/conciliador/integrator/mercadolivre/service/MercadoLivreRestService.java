package br.silveira.conciliador.integrator.mercadolivre.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreTokenDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreUserInfoDto;

@FeignClient(name = "mercadoLivreService", url = "${mercadolivre.url}")
public interface MercadoLivreRestService {

	@RequestMapping(method = RequestMethod.GET, value = "/orders/{ORDER_ID}", produces = "application/json")
	public MercadoLivreOrderDto getOrder(@RequestHeader(value = "Authorization", required = true) String bearerToken,	@PathVariable("ORDER_ID") String orderId);
	
	
	
	@RequestMapping(method = RequestMethod.POST, value="/oauth/token" , produces = "application/json" , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public MercadoLivreTokenDto getToken(@RequestBody Map<String, ?> form);
	
	@RequestMapping(method = RequestMethod.POST, value="/oauth/token" )
	public MercadoLivreTokenDto refreshToken(@RequestParam("grant_type") String grantType, @RequestParam("client_id") String clientId, @RequestParam("client_secret") String clientSecret, @RequestParam("refresh_token") String refreshToken);


	@RequestMapping(method = RequestMethod.GET, value = "/users/me", produces = "application/json")
	public MercadoLivreUserInfoDto getUserInfo(@RequestHeader(value = "Authorization", required = true) String bearerToken);
	
	
	/**
	class Configuration {
        @Bean
        Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
            return new SpringFormEncoder(new SpringEncoder(converters));
        }
    }
    **/
	
}
