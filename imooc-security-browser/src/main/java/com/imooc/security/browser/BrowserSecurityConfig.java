package com.imooc.security.browser;

import com.imooc.security.core.SecurityConstant;
import com.imooc.security.core.authentication.FormAuthenticationConfig;
import com.imooc.security.core.authentication.RememberMeAuthenticationConfig;
import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zhengquan
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    private RememberMeAuthenticationConfig rememberMeAuthenticationConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        formAuthenticationConfig.configure(http);
        rememberMeAuthenticationConfig.configure(http);
        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstant.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        SecurityConstant.DEFAULT_LOGIN_PAGE_URL).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
