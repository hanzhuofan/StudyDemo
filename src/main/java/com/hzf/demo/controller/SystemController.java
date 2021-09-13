package com.hzf.demo.controller;

import com.hzf.demo.beans.vo.LoginUserVO;
import com.hzf.demo.common.Constants;
import com.hzf.demo.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuofan.han
 * @date 2021/9/13
 */
@RestController
@RequiredArgsConstructor
public class SystemController {
    @PostMapping(value = "/login", produces = {Constants.CONTENT_TYPE})
    public Result<?> login(@Validated @RequestBody LoginUserVO loginUserVO) {
        return Result.ok(loginUserVO);
    }
}
