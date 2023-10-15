package com.example.session.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
public class SessionController {
    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "세션이 없습니다.";
        }
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={} value={}", name, session.getAttribute(name)));
        log.info("sessionId={}", session.getId());
        log.info("maxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        log.info("lastAccessedTime={}",new Date(session.getLastAccessedTime()));
        log.info("isNew={}",session.isNew());
        return "세션 출력";
    }

    @RequestMapping("/session-create")
    public String sessionCreate(HttpServletRequest request){
        HttpSession session=request.getSession(true);
        session.setAttribute("ip",request.getRemoteAddr());
        return "세션 생셩";
    }

    @RequestMapping("")
    public String error(){
        throw new RuntimeException("error");
    }
}
