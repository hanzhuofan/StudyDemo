package com.hzf.demo.beans.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author zhuofan.han
 * @date 2021/9/3
 */
@Data
public class DepartmentDTO {
    private final List<DepartmentDTO> children = new ArrayList<>();
    private Long id;
    private String name;
    private String description;
    private Integer status;
    private Long userId;
    private Long parentId;
}
