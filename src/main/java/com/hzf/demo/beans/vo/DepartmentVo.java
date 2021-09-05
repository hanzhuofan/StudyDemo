package com.hzf.demo.beans.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhuofan.han
 * @date 2021/9/3
 */
@Data
public class DepartmentVo {
    private Long id;

    private String name;

    private String description;

    private Integer status;

    private Long userId;

    private Long parentId;

    private final List<DepartmentVo> children = new ArrayList<>();
}
