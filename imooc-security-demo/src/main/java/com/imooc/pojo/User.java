package com.imooc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhengquan
 * @date 2019/8/23
 */
@TableName("t_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private String username;
    private String password;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    @TableLogic
    private Boolean isDeleted;
}
