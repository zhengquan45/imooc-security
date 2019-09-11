package com.imooc.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.mapper.UserMapper;
import com.imooc.param.UserQueryParam;
import com.imooc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhengquan
 * @date 2019/8/24
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> query(UserQueryParam userQueryParam) {

        return userMapper.selectList(Wrappers.<User>lambdaQuery().like(User::getName, userQueryParam.getName()));
    }

    public User create(User user) {
       int resultCount = userMapper.insert(user);
       if(resultCount>0){
           return user;
       }
       return null;
    }

    public User getInfo(Long id) {
        return userMapper.selectById(id);
    }

    public User update(User user) {
        int resultCount = userMapper.updateById(user);
        if(resultCount>0){
            return user;
        }
        return null;
    }

    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    public User loadUserByUsername(String username) {
        return userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,username));
    }
}
