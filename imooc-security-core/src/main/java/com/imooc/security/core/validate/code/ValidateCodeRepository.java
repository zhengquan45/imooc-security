package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码存取器
 *
 * @author zhengquan
 * @date 2019/9/1
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     *
     * @param request
     * @param code
     * @param type
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type);

    /**
     * 获取验证码
     *
     * @param request
     * @param type
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType type);

    /**
     * 移除验证码
     *
     * @param request
     * @param type
     */
    void remove(ServletWebRequest request, ValidateCodeType type);
}
