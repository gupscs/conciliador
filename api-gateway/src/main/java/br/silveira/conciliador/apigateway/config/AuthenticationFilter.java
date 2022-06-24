package br.silveira.conciliador.apigateway.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GatewayFilter {
	
	private static final Logger log = LogManager.getLogger(AuthenticationFilter.class);

    @Autowired
    private RouterValidator routerValidator;
    
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        
        log.info("AuthenticationFilter.filter() : "+request.getURI().getPath());
        
        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request))
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

            final String token = this.getAuthHeader(request);
            try {
            	Claims tokenClaims = jwtUtil.getAllClaimsFromToken(token);
            	if (jwtUtil.isTokenExpired(tokenClaims))
            		return this.onError(exchange, "Authorization header is invalid - Token Expired", HttpStatus.UNAUTHORIZED);
            	if(isTokenInvalidForCompanyId(request, tokenClaims))
            		return this.onError(exchange, "Authorization header is invalid - Token not match with company id", HttpStatus.UNAUTHORIZED);
            }catch(ExpiredJwtException jwte) {
            	return this.onError(exchange, "Authorization header is invalid - Token Expired - Detail Message: "+jwte.getMessage(), HttpStatus.UNAUTHORIZED);
        	}catch(Exception e) {
            	return this.onError(exchange, "Authorization header is invalid - Exception Message: "+e.toString(), HttpStatus.UNAUTHORIZED);
            }

        }
        return chain.filter(exchange);
    }


    /*PRIVATE*/

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
    	log.info("AuthenticationFilter.onError() : "+err);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().set("error-detail", err);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        String token = request.getHeaders().getOrEmpty("Authorization").get(0);
        token = token == null ? "" : token.replaceAll("Bearer", "").trim();
        return token;
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private boolean isTokenInvalidForCompanyId(ServerHttpRequest request, Claims tokenClaims) {
    	//TODO NO OAUTH IMPLEMENTAR EM ADICIONAR COMPANY ID NO TOKEN 
    	/*
    	String tokenCompanyId = String.valueOf(tokenClaims.get("companyId"));
    	String headerCompanyId = request.getHeaders().getOrEmpty("companyId").get(0);
    	return tokenCompanyId.equals(headerCompanyId);
    	*/
    	return false;
    }
}
