package com.imooc.security.core.validate.code;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 验证码生成器集合
 *
 * @author Administrator
 */
@Component
public class ValidateCodeGeneratorHolder {
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    /**
     * 根据验证码类型枚举查找生成器
     *
     * @param type
     * @return
     */
    public ValidateCodeGenerator findValidateCodeGenerator(ValidateCodeType type) {
        return findValidateCodeGenerator(type.toString().toLowerCase());
    }

    /**
     * 根据验证码类型查找生成器
     *
     * @param type
     * @return
     */
    public ValidateCodeGenerator findValidateCodeGenerator(String type) {
        String name = StrUtil.replace(ValidateCodeGenerator.class.getSimpleName(), "Validate",type.toLowerCase());
        ValidateCodeGenerator generator = validateCodeGenerators.get(name);
        if (generator == null) {
            throw new ValidateCodeException("验证码生成器" + name + "不存在");
        }
        return generator;
    }
}
