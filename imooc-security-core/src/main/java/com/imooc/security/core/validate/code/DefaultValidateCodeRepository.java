package com.imooc.security.core.validate.code;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.context.request.ServletWebRequest;

public class DefaultValidateCodeRepository implements  ValidateCodeRepository {

    //30分钟的cache
    private TimedCache<String, ValidateCode> cache = CacheUtil.newTimedCache(1800000);

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
        cache.put(buildKey(request, type), code);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        return cache.get(buildKey(request, type));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        cache.remove(buildKey(request, type));
    }

    /**
     * @param request
     * @param type
     * @return
     */
    private String buildKey(ServletWebRequest request, ValidateCodeType type) {
        String deviceId = request.getHeader("deviceId");
        if (StrUtil.isBlank(deviceId)) {
            throw new ValidateCodeException("请在请求头中携带deviceId参数");
        }
        return "code:" + type.toString().toLowerCase() + ":" + deviceId;
    }
}
