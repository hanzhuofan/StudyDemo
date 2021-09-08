package com.hzf.demo.convert;

import com.hzf.demo.beans.domain.UserDO;
import com.hzf.demo.beans.dto.LoginUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

/**
 * @author zhuofan.han
 * @date 2021/9/8
 */
@Mapper(mappingControl = DeepClone.class)
public interface Cloner {
    Cloner INSTANCE = Mappers.getMapper(Cloner.class);

    @Mapping(target = "lang", ignore = true)
    void copy(UserDO userDO, @MappingTarget LoginUserDTO loginUserDTO);
}
