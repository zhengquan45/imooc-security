package com.imooc.security.browser;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhengquan
 */
@Slf4j
@Component("ImoocAuthenticationSuccessHandler")
public class ImoocAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
           log.info("登录成功");
           response.setContentType("application/json;charset=UTF-8");
           response.getWriter().write(JSONUtil.toJsonStr(authentication));
    }
}
