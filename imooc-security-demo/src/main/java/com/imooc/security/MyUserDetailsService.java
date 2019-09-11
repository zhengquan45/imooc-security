package com.imooc.security;

import com.imooc.pojo.User;
import com.imooc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.loadUserByUsername(username);
        log.info("登录用户名:{}", username);
        return new SocialUser(user.getUsername(), user.getPassword(), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
