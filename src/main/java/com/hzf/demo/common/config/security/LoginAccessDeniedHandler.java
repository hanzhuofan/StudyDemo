package com.hzf.demo.common.config.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.hzf.demo.common.Constants;
import com.hzf.demo.common.Result;
import com.hzf.demo.common.ResultEnum;
import com.hzf.demo.utils.JSON;

/**
 * 解决认证过的用户访问无权限资源时的异常
 *
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Component
public class LoginAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType(Constants.CONTENT_TYPE);
        response.getWriter().write(
            JSON.toJSONString(Result.of(ResultEnum.NO_RIGHT, new String[] {accessDeniedException.getMessage()})));
    }
}
