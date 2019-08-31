package com.imooc.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void sender(String destination, String code) {
      log.info("向{}发送验证码:{}",destination,code);
    }
}
