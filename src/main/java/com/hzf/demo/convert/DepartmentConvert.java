package com.hzf.demo.convert;

import com.hzf.demo.beans.domain.Department;
import com.hzf.demo.beans.dto.DepartmentDto;
import com.hzf.demo.beans.vo.DepartmentVo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hanzhuofan
 * @date 2021/9/4 14:12
 */
@Mapper(componentModel = "spring")
public interface DepartmentConvert {
    DepartmentVo do2vo(Department department);

    List<DepartmentVo> do2vos(List<Department> departments);

    @AfterMapping
    default List<DepartmentVo> treeifyDepartmentVo(@MappingTarget List<DepartmentVo> departmentVos) {
        Map<Long, DepartmentVo> dtoMap = departmentVos.stream().collect(Collectors.toMap(DepartmentVo::getId, a -> a));
        List<DepartmentVo> resultList = new ArrayList<>();
        for (Map.Entry<Long, DepartmentVo> entry : dtoMap.entrySet()) {
            DepartmentVo node = entry.getValue();
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

    DepartmentDto do2dto(Department department);

    List<DepartmentDto> do2dtos(List<Department> departments);

    @AfterMapping
    default List<DepartmentDto> treeifyDepartmentDto(@MappingTarget List<DepartmentDto> departmentVos) {
        Map<Long, DepartmentDto> dtoMap = departmentVos.stream().collect(Collectors.toMap(DepartmentDto::getId, a -> a));
        List<DepartmentDto> resultList = new ArrayList<>();
        for (Map.Entry<Long, DepartmentDto> entry : dtoMap.entrySet()) {
            DepartmentDto node = entry.getValue();
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
