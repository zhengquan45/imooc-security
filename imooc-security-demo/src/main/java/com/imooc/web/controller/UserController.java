package com.imooc.web.controller;

import com.imooc.param.UserQueryParam;
import com.imooc.pojo.User;
import com.imooc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zhengquan
 * @date 2019/8/23
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> query(UserQueryParam userQueryParam) {
        log.info("参数:{}", userQueryParam.toString());
        return userService.query(userQueryParam);
    }

    @GetMapping("/{id:\\d+}")
    public User getInfo(@PathVariable Long id) {
        return userService.getInfo(id);
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping
    public User create( @Valid @RequestBody User user) {
        log.info("参数:{}", user.toString());
        return userService.create(user);
    }

}
