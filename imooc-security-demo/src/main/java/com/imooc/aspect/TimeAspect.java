package com.imooc.aspect;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author zhengquan
 * @date 2019/8/25
 */
@Aspect
@Component
@Slf4j
public class TimeAspect {

    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        log.info("TimeAspect start");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            log.info("arg is:{}",arg.toString());
        }
        long startTime = DateUtil.date().getTime();
        Object proceed = pjp.proceed();
        long endTime = DateUtil.date().getTime();
        log.info("TimeAspect end 耗时:{}",endTime-startTime);
        return proceed;

    }

}
