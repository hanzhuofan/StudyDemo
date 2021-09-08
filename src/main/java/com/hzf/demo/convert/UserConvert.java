package com.hzf.demo.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

import com.hzf.demo.beans.domain.UserDO;
import com.hzf.demo.beans.dto.LoginUserDTO;
import com.hzf.demo.beans.vo.LoginUserVO;

/**
 * @author hanzhuofan
 * @date 2021/9/4 14:12
 */
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    LoginUserDTO vo2dto(LoginUserVO loginUserVO);

    @Mapping(target = "lang", ignore = true)
    LoginUserDTO do2dto(UserDO userDO);

    @Mapping(target = "lang", ignore = true)
    void copy(UserDO userDO, @MappingTarget LoginUserDTO loginUserDTO);
}
