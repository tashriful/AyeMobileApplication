package com.aye.web.filter;

import com.aye.web.utills.JwtUtills;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {


    @Autowired
    private RouteValidator routeValidator;


    @Autowired
    private JwtUtills jwtUtills;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return setUnauthorizedResponse(exchange, "Missing Authorization Header!");
                }

                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtills.validateToken(authHeader);

                } catch (ExpiredJwtException e) {
                    // Token has expired
                    return setUnauthorizedResponse(exchange, "Access Token has expired");
                } catch (MalformedJwtException e) {
                    // Token is malformed or invalid
                    return setUnauthorizedResponse(exchange, "Malformed or invalid token");
                } catch (SignatureException e) {
                    // Token signature is invalid
                    return setUnauthorizedResponse(exchange, "Invalid token signature");
                } catch (JwtException e) {
                    // Other JWT-related exceptions
                    return setUnauthorizedResponse(exchange, "JWT validation failed");
                } catch (Exception e) {
                    // Catch-all for other exceptions
                    return setUnauthorizedResponse(exchange, "Unauthorized access to the application");
                }
            }

            return chain.filter(exchange);
        }));
    }

    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String errorMessage) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                .bufferFactory()
                .wrap(("{\"message\":\"" + errorMessage + "\"}").getBytes())));
    }

    public static class Config {
    }

}
