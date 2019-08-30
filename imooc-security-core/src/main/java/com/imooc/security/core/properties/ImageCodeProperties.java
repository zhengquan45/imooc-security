package com.imooc.security.core.properties;

import lombok.Getter;

@Getter
public class ImageCodeProperties {

    private int width = 300;
    private int height = 100;
    private int length = 4;
    private int expireIn = 60;
    private int thickness = 10;
    private String url;

}
