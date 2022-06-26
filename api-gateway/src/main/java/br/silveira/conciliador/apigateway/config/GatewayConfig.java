package br.silveira.conciliador.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Autowired
	private AuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes().route("oauth", r -> r.path("/oauth/**").filters(f -> f.filter(filter)).uri("lb://oauth"))
				.route("sysadmin", r -> r.path("/sysadmin/**").filters(f -> f.filter(filter)).uri("lb://sysadmin"))
				.route("cost-calculation", r -> r.path("/cost-calculation/**").filters(f -> f.filter(filter)).uri("lb://cost-calculation"))
				.route("mktplace-integrator", r -> r.path("/mktplace-integrator/**").filters(f -> f.filter(filter)).uri("lb://mktplace-integrator"))
				.route("orders", r -> r.path("/orders/**").filters(f -> f.filter(filter)).uri("lb://orders"))
				.route("organizational", r -> r.path("/organizational/**").filters(f -> f.filter(filter)).uri("lb://organizational"))
				.build();
	}

}