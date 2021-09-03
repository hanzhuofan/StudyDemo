package com.hzf.demo.vo;

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
public class DepartmentVO {
    private Long id;

    private String name;

    private String description;

    private Integer status;

    private Long userId;

    private Long parentId;

    private final List<DepartmentVO> children = new ArrayList<>();

    private static List<DepartmentVO> getTree(List<DepartmentVO> list) {
        Map<Long, DepartmentVO> dtoMap = list.stream().collect(Collectors.toMap(DepartmentVO::getId, a -> a));
        List<DepartmentVO> resultList = new ArrayList<>();
        for (Map.Entry<Long, DepartmentVO> entry : dtoMap.entrySet()) {
            DepartmentVO node = entry.getValue();
            if (node.getParentId() == null) {
                resultList.add(node);
            } else {
                if (dtoMap.containsKey(node.getParentId())) {
                    dtoMap.get(node.getParentId()).getChildren().add(node);
                }
            }
        }
        return resultList;
    }
}
