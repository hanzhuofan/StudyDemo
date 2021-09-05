package com.hzf.demo.beans.vo;

import javax.validation.constraints.NotBlank;

/**
 * @author zhuofan.han
 * @date 2021/9/3
 */
public class OrganizationVo {
    private Long id;

    @NotBlank
    private String orgName;

    private String orgAlias;

    private String orgDescription;
}
