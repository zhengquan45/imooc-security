package com.imooc.security.core.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 安全模块配置项
 */
@Getter
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

    /**
     * 浏览器环境配置项
     */
    private BrowserProperties browser = new BrowserProperties();
    /**
     * 验证码配置项
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

}
