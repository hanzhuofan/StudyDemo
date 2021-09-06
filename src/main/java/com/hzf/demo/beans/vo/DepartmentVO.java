package com.hzf.demo.beans.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuofan.han
 * @date 2021/9/3
 */
@Data
public class DepartmentVO {
    private Long id;

    private String name;

    private String description;

    private Integer status;

    private Long userId;

    private Long parentId;

    private final List<DepartmentVO> children = new ArrayList<>();
}
