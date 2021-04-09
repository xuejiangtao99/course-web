package com.zuul.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Component
public class LoginAdminGatewayFilter implements GatewayFilter, Ordered {


    @Resource
    private RedisTemplate redisTemplate;

    private static final Logger LOG =  LoggerFactory.getLogger(LoginAdminGatewayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        //如果路径中包含/admin/login 则为登录，不需要拦截
        if(
                path.contains("/system/admin/login")
                || path.contains("/system/admin/logout")
                || path.contains("/system/admin/image-code")
        ){
            LOG.info("不需要登录验证：{}",path);
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst("token");
        LOG.info("token的值：{}",token);
        if(token == null || token.isEmpty()){
            LOG.info("token的值为空,请求拦截：{}");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        Object object = redisTemplate.opsForValue().get(token);
        if(object == null){
            LOG.warn("token无效，请求被拦截：{}");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }else{
            LOG.info("token生效：{}",object);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
