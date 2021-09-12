package com.hzf.demo.beans.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.hzf.demo.common.validation.groups.Add;
import com.hzf.demo.common.validation.groups.Update;
import lombok.Data;

/**
 * @author zhuofan.han
 * @date 2021/9/3
 */
@Data
public class OrganizationVO {
    @Null(groups = Add.class)
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank
    private String orgName;

    @NotBlank
    private String orgAlias;

    private String orgDescription;
}
