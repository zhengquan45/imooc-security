package com.imooc.security.core.social.qq.connect;

import com.imooc.security.core.social.qq.api.QQ;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;

/**
 * @author Administrator
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {


    public QQServiceProvider(OAuth2Operations oauth2Operations) {
        super(oauth2Operations);
    }

    @Override
    public QQ getApi(String accessToken) {
        return null;
    }
}
