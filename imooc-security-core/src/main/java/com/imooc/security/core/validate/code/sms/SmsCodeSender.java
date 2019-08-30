package com.imooc.security.core.validate.code.sms;

public interface SmsCodeSender {
    void sender(String destination,String code);
}
