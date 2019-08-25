package com.imooc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengquan
 * @date 2019/7/14
 */
@Configuration
@MapperScan("com.imooc.mapper")
public class MybatisPlusConfiguration {

}
