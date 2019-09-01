package com.imooc.security.core.support;

import lombok.Data;

/**
 * 回复
 *
 * @author zhengquan
 * @date 2019/9/1
 */
@Data
public class R<T> {
    private T data;
    private String msg;

    public R(T data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public R(T data) {
        this.data = data;
    }

    public R(String msg) {
        this.msg = msg;
    }
}
