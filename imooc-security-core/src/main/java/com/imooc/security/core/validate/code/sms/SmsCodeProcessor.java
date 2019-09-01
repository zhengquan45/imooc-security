package com.imooc.security.core.validate.code.sms;

import com.imooc.security.core.SecurityConstant;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 *
 * @author zhengquan
 * @date 2019/8/31
 */
@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode smsCode) throws Exception {
        String paramName = SecurityConstant.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getStringParameter(request.getRequest(), paramName);
        smsCodeSender.sender(mobile, smsCode.getCode());
    }
}
