package com.hzf.demo.utils;

import com.hzf.demo.beans.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
public class SpringSecurityUtils {
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
