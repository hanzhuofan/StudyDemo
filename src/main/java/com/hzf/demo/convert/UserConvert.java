package com.hzf.demo.convert;

import com.hzf.demo.beans.domain.UserDO;
import com.hzf.demo.beans.dto.LoginUserDTO;
import com.hzf.demo.beans.vo.LoginUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hanzhuofan
 * @date 2021/9/4 14:12
 */
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    LoginUserDTO vo2dto(LoginUserVO loginUserVO);

    LoginUserVO dto2vo(LoginUserDTO loginUserDTO);

    LoginUserDTO do2dto(UserDO userDO);
}
