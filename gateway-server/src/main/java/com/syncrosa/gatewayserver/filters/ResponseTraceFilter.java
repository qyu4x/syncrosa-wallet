package com.syncrosa.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ResponseTraceFilter  implements Ordered, GlobalFilter {

    private final String X_SYNCROSA_TRACE_ID = "X-Syncrosa-Trace-Id";
    private Logger log = LoggerFactory.getLogger(RequestTraceFilter.class);


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    String traceId = exchange.getRequest().getHeaders().get(X_SYNCROSA_TRACE_ID).get(0);
                    log.info("Updated the {} to the outbound headers: {}", X_SYNCROSA_TRACE_ID, traceId);
                    exchange.getResponse().getHeaders()
                            .add(X_SYNCROSA_TRACE_ID, traceId);
                }));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
