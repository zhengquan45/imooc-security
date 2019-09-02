package com.imooc.security.core;

/**
 * @author zhengquan
 * @date 2019/9/1
 */
public interface SecurityConstant {
    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * 默认请求需要身份认证的跳转
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    /**
     * 默认用户名密码登录的请求处理url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 默认手机验证码登录的请求处理url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE ="/authentication/mobile";

    /**
     * 默认的登录页面
     */
    String DEFAULT_LOGIN_PAGE_URL ="/default-test-signIn.html";

    /**
     * 验证图形验证码时,http请求默认携带的图形验证码信息参数名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE="imageCode";

    /**
     * 验证短信验证码时,http请求默认携带的短信验证码信息参数名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS="smsCode";

    /**
     * 发送短信验证码 或 验证短信登录时,传递手机号的参数名称
     */
    String DEFAULT_PARAMETER_NAME_MOBILE="mobile";

}
