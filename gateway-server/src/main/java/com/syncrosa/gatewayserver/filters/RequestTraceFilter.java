package com.syncrosa.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Component
public class RequestTraceFilter implements Ordered, GlobalFilter {

    private final String X_SYNCROSA_TRACE_ID = "X-Syncrosa-Trace-Id";
    private Logger log = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> traceIds = exchange.getRequest().getHeaders()
                .get(X_SYNCROSA_TRACE_ID);
        if (!traceIds.isEmpty()) {
            log.debug("{} FOUND in {}: {}", X_SYNCROSA_TRACE_ID, this.getClass(), traceIds.get(0));
        } else {
            String traceId = UUID.randomUUID().toString();
            exchange.mutate().request(request -> request.header(X_SYNCROSA_TRACE_ID, traceId)).build();
            log.debug("{} GENERATED in {}: {}", X_SYNCROSA_TRACE_ID, this.getClass(), traceIds.get(0));
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
