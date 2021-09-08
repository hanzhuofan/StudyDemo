package com.hzf.demo.beans.vo;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author zhuofan.han
 * @date 2021/9/3
 */
@Data
public class OrganizationVO {
    private Long id;

    @NotBlank
    private String orgName;

    @NotBlank
    private String orgAlias;

    private String orgDescription;
}
