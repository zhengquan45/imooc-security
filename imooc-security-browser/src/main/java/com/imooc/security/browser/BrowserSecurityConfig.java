package com.imooc.security.browser;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Autowired
    public AuthenticationFailureHandler imoocAuthenticationFailHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(imoocAuthenticationFailHandler)
                .and()
                .rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getCode().getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require","/code/image","/index.html").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
