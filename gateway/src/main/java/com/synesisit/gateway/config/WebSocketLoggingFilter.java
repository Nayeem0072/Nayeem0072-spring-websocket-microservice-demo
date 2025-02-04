package com.synesisit.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class WebSocketLoggingFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketLoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (isWebSocketRequest(exchange)) {
            logger.info("WebSocket request received: {}", exchange.getRequest().getURI());
        }
        return chain.filter(exchange);
    }

    private boolean isWebSocketRequest(ServerWebExchange exchange) {
        return exchange.getRequest().getHeaders().containsKey("Upgrade")
                && "websocket".equalsIgnoreCase(exchange.getRequest().getHeaders().getUpgrade());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
