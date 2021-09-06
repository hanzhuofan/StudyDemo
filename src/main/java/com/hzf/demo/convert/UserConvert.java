package com.hzf.demo.convert;

import com.hzf.demo.beans.dto.LoginUserDTO;
import com.hzf.demo.beans.vo.LoginUserVO;
import org.mapstruct.Mapper;

/**
 * @author hanzhuofan
 * @date 2021/9/4 14:12
 */
@Mapper(componentModel = "spring")
public interface UserConvert {
    LoginUserDTO vo2dto(LoginUserVO loginUserVO);

    LoginUserVO dto2vo(LoginUserDTO loginUserDTO);
}
