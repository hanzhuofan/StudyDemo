package com.hzf.demo.beans.dto;

import com.hzf.demo.beans.domain.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Data
public class LoginUserDTO {
    private String username;

    private String password;

    private String lang;

    private Set<Role> authorities = new HashSet<>();
}
