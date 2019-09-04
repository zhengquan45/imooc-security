package com.imooc.security.browser;

import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeRepository;
import com.imooc.security.core.validate.code.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Administrator
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
        sessionStrategy.setAttribute(request,getSessionKey(type),code);
    }

    private String getSessionKey(ValidateCodeType type) {
        return "code:"+type.toString().toLowerCase();
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        return (ValidateCode) sessionStrategy.getAttribute(request,getSessionKey(type));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        sessionStrategy.removeAttribute(request,getSessionKey(type));
    }
}
