package com.imooc.security.core.social.qq.api;

/**
 * @author Administrator
 */
public interface QQ {
    QQUserInfo getUserInfo();

    /**
     * @return
     */
    String getOpenId();
}
