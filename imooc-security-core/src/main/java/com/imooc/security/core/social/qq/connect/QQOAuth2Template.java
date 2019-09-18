package com.imooc.security.core.social.qq.connect;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Slf4j
public class QQOAuth2Template extends OAuth2Template {

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String response = getRestTemplate().postForObject(accessTokenUrl,parameters,String.class);
        log.info("accessToken的响应:{}",response);
        String[] strings = StrUtil.split(response, "&");
        String accessToken = StrUtil.subAfter(strings[0],"=",true);
        Long expiresIn = Long.parseLong(StrUtil.subAfter(strings[1],"=",true));
        String refreshToken = StrUtil.subAfter(strings[2],"=",true);
        return new AccessGrant(accessToken,null,refreshToken,expiresIn);
    }
}
