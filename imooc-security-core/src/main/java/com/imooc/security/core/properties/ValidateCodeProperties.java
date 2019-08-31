package com.imooc.security.core.properties;

import lombok.Getter;

@Getter
public class ValidateCodeProperties {

    private BrowserProperties browser = new BrowserProperties();
    private ImageCodeProperties image = new ImageCodeProperties();
    private SmsCodeProperties sms = new SmsCodeProperties();


}
