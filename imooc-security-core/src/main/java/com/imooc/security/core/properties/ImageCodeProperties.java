package com.imooc.security.core.properties;

import lombok.Getter;

/**
 * @author Administrator
 */
@Getter
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    private int width = 300;
    private int height = 100;
    private int thickness = 10;

}
