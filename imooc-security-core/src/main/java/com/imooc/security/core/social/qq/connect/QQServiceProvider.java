package com.imooc.security.core.social.qq.connect;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQimpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author Administrator
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;
    public static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    public static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId,String appSecret) {
        super(new QQOAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQimpl(accessToken,appId);
    }
}
