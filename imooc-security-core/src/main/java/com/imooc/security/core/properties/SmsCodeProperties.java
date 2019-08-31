package com.imooc.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhengquan
 * @date 2019/8/31
 */
@Getter
@Setter
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;
    private String url;

}
