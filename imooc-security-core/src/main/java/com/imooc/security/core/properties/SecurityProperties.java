package com.imooc.security.core.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "imooc.security")
@Getter
public class SecurityProperties {

    private ValidateCodeProperties code = new ValidateCodeProperties();

}
