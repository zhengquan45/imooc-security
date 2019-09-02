package com.imooc.security.core.properties;

import lombok.Getter;

/**
 * 验证码配置项
 */
@Getter
public class ValidateCodeProperties {

    /**
     * 图形验证码配置项
     */
    private ImageCodeProperties image = new ImageCodeProperties();
    /**
     * 短信验证码配置项
     */
    private SmsCodeProperties sms = new SmsCodeProperties();


}
