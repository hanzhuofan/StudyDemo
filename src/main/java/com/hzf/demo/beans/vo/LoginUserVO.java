package com.hzf.demo.beans.vo;

import java.util.Locale;

import lombok.Data;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Data
public class LoginUserVO {
    private String username;

    private String password;

    private String lang = Locale.CHINA.toString();
}
