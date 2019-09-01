package com.imooc.security.core.validate.code.image;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import lombok.Setter;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图形验证码生成器
 *
 * @author zhengquan
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

    @Setter
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(ServletWebRequest request) {
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());
        int length = ServletRequestUtils.getIntParameter(request.getRequest(), "length", securityProperties.getCode().getImage().getLength());
        int thickness = ServletRequestUtils.getIntParameter(request.getRequest(), "thickness", securityProperties.getCode().getImage().getThickness());
        int expireIn = ServletRequestUtils.getIntParameter(request.getRequest(), "expireIn", securityProperties.getCode().getImage().getExpireIn());
        AbstractCaptcha captcha = CaptchaUtil.createShearCaptcha(width, height, length, thickness);
        return new ImageCode(captcha.getImage(), captcha.getCode(), expireIn);
    }
}
