package com.synesisit.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class CustomRouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("websocket_route", r -> r
                        .path("/chat/**")
                        .or()
                        .method(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)
                        .and()
                        .path("/chat/**")
                        //.uri("http://localhost:8081"))
                        .uri("lb://backend/chat"))
                .build();
    }
}
