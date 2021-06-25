package com.example.sleuth.demo.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Before("execution(* *..*Controller.*(..))")
    public void startLog(JoinPoint joinPoint) {
        log.info("メソッド開始: " + joinPoint.getSignature());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (ObjectUtils.isEmpty(request.getQueryString())) {
            log.info("{} {}", request.getMethod(), request.getRequestURI());
        } else {
            log.info("{} {}?{}", request.getMethod(), request.getRequestURI(), request.getQueryString());
        }
        List<Object> args = Arrays.stream(joinPoint.getArgs()).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(args)) {
            args.stream()
                    .map(arg -> {
                        try {
                            return new ObjectMapper().writeValueAsString(arg);
                        } catch (JsonProcessingException e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .forEach(arg -> {
                        if (arg.matches("^\\{.*}$")) {
                            log.info("request body: {}", arg);
                        }
                    });
        }
    }
}