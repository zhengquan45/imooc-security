package com.imooc.security.core.social.qq.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author Administrator
 */
@Slf4j
public class QQimpl extends AbstractOAuth2ApiBinding implements QQ{

    public static final String URL_GET_OPENID = "https://graph.qq.com.oauth2.0/me?access_token=%s";

    public static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    public QQimpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID,accessToken);
        String result = getRestTemplate().getForObject(url,String.class);
        log.info("请求:{},accessToken:{},openId的响应:{}",URL_GET_OPENID,accessToken,result);
        openId = StrUtil.subBetween(result, "\"openid\":\", \"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USER_INFO,appId,openId);
        String result = getRestTemplate().getForObject(url, String.class);
        QQUserInfo qqUserInfo;
        try {
            qqUserInfo = JSONUtil.toBean(result, QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
        }catch (Exception e){
            throw new RuntimeException("获取用户信息失败",e);
        }
        log.info("请求:{},appId:{},openId:{},用户信息的响应:{}",URL_GET_USER_INFO,appId,openId,result);
        return qqUserInfo;
    }

    @Override
    public String getOpenId() {
        return openId;
    }
}
