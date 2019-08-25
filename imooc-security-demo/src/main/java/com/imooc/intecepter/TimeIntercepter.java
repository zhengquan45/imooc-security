package com.imooc.intecepter;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhengquan
 * @date 2019/8/25
 */
@Component
@Slf4j
public class TimeIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("time intercepter preHandle");
        httpServletRequest.setAttribute("startTime", DateUtil.date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("time intercepter postHandle");
        long startTime = (long) httpServletRequest.getAttribute("startTime");
        long endTime = DateUtil.date().getTime();
        log.info("耗时:" + (endTime - startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("time intercepter afterCompletion");
    }
}
