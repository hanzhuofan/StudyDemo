package com.hzf.demo.convert;

import com.hzf.demo.beans.domain.Department;
import com.hzf.demo.beans.dto.DepartmentDTO;
import com.hzf.demo.beans.vo.DepartmentVO;
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
    DepartmentVO do2vo(Department department);

    List<DepartmentVO> do2vos(List<Department> departments);

    @AfterMapping
    default List<DepartmentVO> treeifyDepartmentVo(@MappingTarget List<DepartmentVO> departmentVOS) {
        Map<Long, DepartmentVO> dtoMap = departmentVOS.stream().collect(Collectors.toMap(DepartmentVO::getId, a -> a));
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

    DepartmentDTO do2dto(Department department);

    List<DepartmentDTO> do2dtos(List<Department> departments);

    @AfterMapping
    default List<DepartmentDTO> treeifyDepartmentDto(@MappingTarget List<DepartmentDTO> departmentVos) {
        Map<Long, DepartmentDTO> dtoMap = departmentVos.stream().collect(Collectors.toMap(DepartmentDTO::getId, a -> a));
        List<DepartmentDTO> resultList = new ArrayList<>();
        for (Map.Entry<Long, DepartmentDTO> entry : dtoMap.entrySet()) {
            DepartmentDTO node = entry.getValue();
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
