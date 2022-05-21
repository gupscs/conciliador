package br.silveira.conciliador.apigateway.util;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/oauth/oauth/token",
            "/organizational/organizational/registerCheck/",
            "/organizational/organizational/register",
            "/oauth/check_token",
            "/_ah/**"
            
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}