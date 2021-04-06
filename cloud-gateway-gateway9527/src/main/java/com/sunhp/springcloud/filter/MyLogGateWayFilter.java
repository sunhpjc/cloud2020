package com.sunhp.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("**********myGlobalFilter"+ new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if(StringUtils.isBlank(uname)){
            log.info("*******用户名为空，非法用户，/(ㄒoㄒ)/~~");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    //这里是Order接口，返回过滤器执行顺序，值越小优先级越高
    //也可以不实现这个接口，使用注解@Order(0)代替
    @Override
    public int getOrder() {
        return 0;
    }
}
