package com.example.filter.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        UUID uuid = UUID.randomUUID();
        try {
            log.info("REQUEST [{}][{}]",uuid,requestURI);
            chain.doFilter(request,response);
        }catch (Exception e){
            throw e;
        }finally {
            log.info("RESPONSE [{}][{}]",uuid,requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
        Filter.super.destroy();
    }
}
