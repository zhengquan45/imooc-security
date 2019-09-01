package com.imooc.security.core.validate.code.sms;

/**
 * 短信验证码发送器接口
 *
 * @author zhengquan
 */
public interface SmsCodeSender {
    /**
     * 发送验证码方法
     *
     * @param destination
     * @param code
     */
    void send(String destination, String code);
}
