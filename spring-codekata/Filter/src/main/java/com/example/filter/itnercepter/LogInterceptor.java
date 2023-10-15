package com.example.filter.itnercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        UUID uuid = UUID.randomUUID();

        log.info("REQUEST [{}][{}][{}]",uuid,requestURI,handler);
        HttpSession session = request.getSession();
        session.setAttribute(LOG_ID,uuid.toString());
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle [ModelAndView {}]",modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();
        String logId = (String) session.getAttribute(LOG_ID);
        log.info("RESPONSE [{}][{}]",logId,requestURI);

    }
}
