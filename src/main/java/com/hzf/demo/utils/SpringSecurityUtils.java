package com.hzf.demo.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.hzf.demo.beans.domain.UserDO;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
public class SpringSecurityUtils {
    public static UserDO getCurrentUser() {
        return (UserDO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
