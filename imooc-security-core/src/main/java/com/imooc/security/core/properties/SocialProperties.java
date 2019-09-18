package com.imooc.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator
 */
@Getter
@Setter
public class SocialProperties{
    private String filterProcessUrl = "/auth";
    private QQProperties qq;
}
