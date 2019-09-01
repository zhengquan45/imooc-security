package com.imooc.security.core.validate.code;

import com.imooc.security.core.SecurityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码控制器
 *
 * @author zhengquan
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @GetMapping(SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }
}
