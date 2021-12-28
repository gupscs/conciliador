package br.silveira.conciliador.apigateway.config;


import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	//TODO PASSAR PARA CONFIGURAÇÕES DINAMICAS PUXANDO DO SERVICO SYSADMIN (ROLE X ACESSO)
	private static final String[] PUBLIC = { "/oauth/token", "/oauth/check_token" , "/actuator/**"};
	
	private static final String[] CUSTOMER_ADMIN = { "/sysadmin/**" , "/orders/**", "/organizational/**"};
	
	private static final String[] CUSTOMER_MANAGER = { "/organizational/**" , "/orders/**" };
	
	private static final String[] CUSTOMER_VIEW = { "/orders/**" };
	
	private static final String[] SUPER_USER = { "/**" };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.httpBasic().disable();
    	http.formLogin()
    	     .and().csrf().disable();
    	http.csrf().disable();
    	
        http
          .authorizeRequests(authz -> authz
    		  .antMatchers(PUBLIC).permitAll()
    		  .antMatchers(CUSTOMER_VIEW).hasAnyRole("CUSTOMER_VIEW")
    		  .antMatchers(CUSTOMER_MANAGER).hasRole("CUSTOMER_MANAGER")
    		  .antMatchers(CUSTOMER_ADMIN).hasRole("CUSTOMER_ADMIN")
    		  .antMatchers(SUPER_USER).hasRole("SUPER_USER")
    		  .anyRequest().authenticated())
          .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken());
	}
}