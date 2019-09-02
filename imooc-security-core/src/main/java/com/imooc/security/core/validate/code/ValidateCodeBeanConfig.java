package com.imooc.security.core.validate.code;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.image.ImageCodeGenerator;
import com.imooc.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.imooc.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码相关的扩展点配置.配置使用的bean.业务系统都可以通过声明同类型或同名的bean来覆盖安全模块的默认配置
 *
 * @author zhengquan
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 图形验证码生成器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    /**
     * 短信验证码发送器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }


    /**
     * 验证码存储器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(ValidateCodeRepository.class)
    public ValidateCodeRepository validateCodeRepository() {
        return new DefaultValidateCodeRepository();
    }

}
