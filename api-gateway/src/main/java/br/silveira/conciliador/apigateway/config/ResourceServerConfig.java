package br.silveira.conciliador.apigateway.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
@EnableWebSecurity
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtTokenStore tokenStore;
	
	//TODO PASSAR PARA CONFIGURAÇÕES DINAMICAS PUXANDO DO SERVICO SYSADMIN (ROLE X ACESSO)
	
	private static final String[] PUBLIC = { "/oauth/token" };
	
	private static final String[] CUSTOMER_ADMIN = { "/sysadmin/**" , "/orders/**", "/organizational/**"};
	
	private static final String[] CUSTOMER_MANAGER = { "/organizational/**" , "/orders/**" };
	
	private static final String[] CUSTOMER_VIEW = { "/orders/**" };
	
	private static final String[] SUPER_USER = { "/**" };
	
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()
		.antMatchers(CUSTOMER_VIEW).hasAnyRole("CUSTOMER_VIEW")
		.antMatchers(CUSTOMER_MANAGER).hasRole("CUSTOMER_MANAGER")
		.antMatchers(CUSTOMER_ADMIN).hasRole("CUSTOMER_ADMIN")
		.antMatchers(SUPER_USER).hasRole("SUPER_USER")
		.anyRequest().authenticated();
		
		//http.cors().configurationSource(corsConfigurationSource());
	}
	
	//@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}
	
	//@Bean
	public CorsFilter corsFilter() {
		CorsFilter bean = new CorsFilter(corsConfigurationSource());
		return bean;
	}
	
}
