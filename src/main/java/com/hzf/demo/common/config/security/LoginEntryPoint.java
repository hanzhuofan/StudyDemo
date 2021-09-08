package com.hzf.demo.common.config.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.hzf.demo.common.Constants;
import com.hzf.demo.common.Result;
import com.hzf.demo.common.ResultEnum;
import com.hzf.demo.utils.JSON;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Component
public class LoginEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException {
        response.setContentType(Constants.CONTENT_TYPE);
        response.getWriter()
            .write(JSON.toJSONString(Result.of(ResultEnum.NO_RIGHT, new String[] {authException.getMessage()})));
    }
}
