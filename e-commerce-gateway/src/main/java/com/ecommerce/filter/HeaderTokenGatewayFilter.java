package com.ecommerce.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <h1> HTTP 请求头部携带 Token 验证过滤器</h1>
 * */
public class HeaderTokenGatewayFilter implements GatewayFilter, Ordered {

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从 HTTP Header 中寻找 Key 为 Token， Value为 imooc的 key value pair
        String name = exchange.getRequest().getHeaders().getFirst("token");
        if ("imooc".equals(name)) {
            return chain.filter(exchange);
        }

        // 标记此次请求没有权限，并结束这一次请求
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
