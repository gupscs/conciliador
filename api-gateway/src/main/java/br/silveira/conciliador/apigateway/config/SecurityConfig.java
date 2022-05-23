package br.silveira.conciliador.apigateway.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
	
	@Value("${cors.allowedOrigins}")
	private String[] ALLOWED_ORIGINS;

	// TODO PASSAR PARA CONFIGURAÃ‡Ã•ES DINAMICAS PUXANDO DO SERVICO SYSADMIN (ROLE X
	// ACESSO) - Cadastro de ROLE x URL e outro de Grupos x ROLE
	private static final String[] PUBLIC = { "/","/_ah/**","/oauth/**", "/oauth/check_token , /**"};

	private static final String[] CUSTOMER_ADMIN = { "/sysadmin/**", "/orders/**", "/organizational/**" };

	private static final String[] CUSTOMER_MANAGER = { "/organizational/**", "/orders/**" };

	private static final String[] CUSTOMER_VIEW = { "/orders/**" };

	private static final String[] SUPER_USER = { "/**" };

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//		http.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated()).oauth2ResourceServer().opaqueToken();
		// TODO IMPLEMENTAR O CORS
		http.csrf().disable().authorizeExchange().pathMatchers(PUBLIC).permitAll()
		.pathMatchers("/**").authenticated().and().oauth2ResourceServer().opaqueToken();
		http.cors().configurationSource(corsConfigurationSource());
		http.redirectToHttps();
		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		//corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		
		corsConfig.setAllowedOrigins(Arrays.asList(ALLOWED_ORIGINS));
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "companyId"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}

}