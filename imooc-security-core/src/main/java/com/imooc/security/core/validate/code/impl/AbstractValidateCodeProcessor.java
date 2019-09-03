package com.imooc.security.core.validate.code.impl;

import cn.hutool.core.util.StrUtil;
import com.imooc.security.core.validate.code.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 抽象图形验证码处理器
 *
 * @author zhengquan
 * @date 2019/8/31
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    @Autowired
    private ValidateCodeGeneratorHolder validateCodeGeneratorHolder;

    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * 发送验证码,由子类实现
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    private void save(ServletWebRequest request, C validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        validateCodeRepository.save(request, code, getValidateCodeType());
    }

    private C generate(ServletWebRequest request) {
        String type = getValidateCodeType().toString().toLowerCase();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorHolder.findValidateCodeGenerator(type);
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * Processor类型判断验证码类型
     *
     * @return
     */
    private ValidateCodeType getValidateCodeType() {
        String type = StrUtil.subBefore(getClass().getSimpleName(), "CodeProcessor",true);
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    @Override
    public void validate(ServletWebRequest request){
        ValidateCodeType codeType = getValidateCodeType();

        C codeInSession = (C) validateCodeRepository.get(request, codeType);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StrUtil.isBlank(codeInRequest)) {
            throw new ValidateCodeException(codeType + "请填写验证码");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(codeType + "验证码不存在");
        }

        if (codeInSession.isExpired()) {
            validateCodeRepository.remove(request, codeType);
            throw new ValidateCodeException(codeType + "验证码已过期，请重新获取");
        }

        if (!StrUtil.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码不正确");
        }

        validateCodeRepository.remove(request, codeType);
    }
}
