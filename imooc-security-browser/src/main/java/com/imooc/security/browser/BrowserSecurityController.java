package com.imooc.security.browser;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhengquan
 */
@RestController
public class BrowserSecurityController {

    @RequestMapping("/authentication/require")
    @ResponseStatus(code= HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response){
        return new SimpleResponse("访问服务需要身份认证,请引导用户到登录页");
    }
}
