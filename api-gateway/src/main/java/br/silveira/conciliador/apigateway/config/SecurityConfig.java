package br.silveira.conciliador.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	// TODO PASSAR PARA CONFIGURAÇÕES DINAMICAS PUXANDO DO SERVICO SYSADMIN (ROLE X  ACESSO) - Cadastro de ROLE x URL e outro de Grupos x ROLE 
	private static final String[] PUBLIC = { "/oauth/**", "/oauth/check_token", "/actuator/**" };

	private static final String[] CUSTOMER_ADMIN = { "/sysadmin/**", "/orders/**", "/organizational/**" };

	private static final String[] CUSTOMER_MANAGER = { "/organizational/**", "/orders/**" };

	private static final String[] CUSTOMER_VIEW = { "/orders/**" };

	private static final String[] SUPER_USER = { "/**" };

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//		http.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated()).oauth2ResourceServer().opaqueToken();
		//TODO IMPLEMENTAR O CORS
		http
			.csrf().disable()
			.authorizeExchange()
				.pathMatchers(PUBLIC).permitAll()
				.pathMatchers(CUSTOMER_ADMIN).hasRole("CUSTOMER_ADMIN")
				.pathMatchers(CUSTOMER_MANAGER).hasRole("CUSTOMER_MANAGER")
				.pathMatchers(CUSTOMER_VIEW).hasRole("CUSTOMER_VIEW")
				.pathMatchers(SUPER_USER).hasRole("SUPER_USER")
				.anyExchange().authenticated()
				.and()
			.oauth2ResourceServer().opaqueToken();
		return http.build();
	}

}