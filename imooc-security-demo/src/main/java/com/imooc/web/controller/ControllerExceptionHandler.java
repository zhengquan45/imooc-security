package com.imooc.web.controller;

import cn.hutool.core.collection.CollUtil;
import com.imooc.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
 * @author zhengquan
 * @date 2019/8/24
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object>handleUserNotExistException(UserNotExistException ex){
      Map<String,Object>result = CollUtil.newHashMap();
      result.put("id",ex.getId());
      result.put("message",ex.getMessage());
      return result;
    }
}
