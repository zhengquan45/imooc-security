package com.imooc.security.core.validate.code;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.imooc.security.core.properties.SecurityProperties;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
    @Setter
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Setter
    private SecurityProperties securityProperties;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = CollUtil.newHashSet();

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StrUtil.split(securityProperties.getCode().getImage().getUrl(), ",");
        urls=CollUtil.newHashSet(configUrls);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean action = false;
        for (String url : urls) {
            if(pathMatcher.match(url,request.getRequestURI())){
                action = true;
            }
        }

        if(action){
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
            }
        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request,ValidateCodeController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"imageCode");
        if(StrUtil.isBlank(codeInRequest)){
            throw new ValidateCodeException("登录请求中请携带验证码");
        }
        if(codeInSession==null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(codeInSession.isExpried()){
            throw new ValidateCodeException("验证码已过期");
        }
        if(!StrUtil.equals(codeInRequest,codeInSession.getCode())){
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
    }

}
