package com.hzf.demo.common.config.security;

import com.hzf.demo.common.Result;
import com.hzf.demo.common.ResultEnum;
import com.hzf.demo.utils.JSON;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Component
public class LoginEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter()
            .write(JSON.toJSONString(Result.of(ResultEnum.NO_RIGHT, new String[] {authException.getMessage()})));
    }
}