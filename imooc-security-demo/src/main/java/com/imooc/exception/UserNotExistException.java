package com.imooc.exception;

import lombok.Data;

/**
 * @author zhengquan
 * @date 2019/8/24
 */
@Data
public class UserNotExistException extends Throwable {
    private Long id;

}
