package com.hzf.demo.beans.vo;

import java.util.Locale;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Data
public class LoginUserVO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String lang = Locale.CHINA.toString();
}
