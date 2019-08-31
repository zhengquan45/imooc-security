package com.imooc.security.core.validate.code.sms;

import cn.hutool.core.util.RandomUtil;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import lombok.Setter;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author zhengquan
 * @date 2019/8/31
 */
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Setter
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        int length = ServletRequestUtils.getIntParameter(request.getRequest(), "length", securityProperties.getCode().getImage().getLength());
        int expireIn = ServletRequestUtils.getIntParameter(request.getRequest(), "expireIn", securityProperties.getCode().getImage().getExpireIn());
        String code = RandomUtil.randomNumbers(length);
        return new ValidateCode(code,expireIn);
    }
}
