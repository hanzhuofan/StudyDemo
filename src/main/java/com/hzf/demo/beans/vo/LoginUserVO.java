package com.hzf.demo.beans.vo;

import java.util.Locale;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Data
public class LoginUserVO {
    @NotBlank
    @ApiModelProperty(example = "admin", required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(example = "abc123", required = true)
    private String password;

    @ApiModelProperty(example = "zh_CN")
    private String lang = Locale.CHINA.toString();
}
