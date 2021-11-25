package br.silveira.conciliador.integrator.mercadolivre.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:config.properties"})
public class MercadoLivreConfig {
	
	@Value("${mercadolivre.notification.topic.orders}")
	public String TOPIC_ORDERS;
	
	@Value("${mercadolivre.notification.topic.createdorders}")
	public String TOPIC_CREATED_ORDERS;
	
	@Value("${mercadolivre.notification.topic.shipments}")
	public String TOPIC_SHIPMENTS;
	
	@Value("${mercadolivre.notification.topic.payments}")
	public String TOPIC_PAYMENTS;
	
	@Value("${mercadolivre.notification.topic.feedback}")
	public String TOPIC_FEEDBACK;
	
	@Value("${meracodlivre.appId}")
	public String APP_ID;
	
	@Value("${mercadolivre.client-secret}")
	public String CLIENT_SECRET;
	
	@Value("${mercadolivre.redirect-url}")
	public String REDIRECT_URL;

}
