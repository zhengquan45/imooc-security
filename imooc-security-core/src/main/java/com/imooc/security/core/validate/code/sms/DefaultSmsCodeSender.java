package com.imooc.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认的短信验证码发送器
 *
 * @author zhengquan
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String destination, String code) {
        log.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
        log.info("向手机{}发送验证码:{}", destination, code);
    }
}
