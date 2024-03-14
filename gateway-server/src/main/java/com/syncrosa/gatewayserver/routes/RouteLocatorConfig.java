package com.syncrosa.gatewayserver.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class RouteLocatorConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(r ->
                        r.path("/syncrosa/accounts/**")
                                .filters(f ->
                                        f.rewritePath("/syncrosa/accounts/(?<segment>.*)","/${segment}")
                                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                )
                                .uri("lb://ACCOUNTS"))
                .route(r ->
                        r.path("/syncrosa/loans/**")
                                .filters(f ->
                                        f.rewritePath("/syncrosa/loans/(?<segment>.*)","/${segment}")
                                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                )
                                .uri("lb://LOANS"))
                .route(r ->
                        r.path("/syncrosa/cards/**")
                                .filters(f ->
                                        f.rewritePath("/syncrosa/cards/(?<segment>.*)","/${segment}")
                                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                                .uri("lb://CARDS"))
                .build();

    }

}
