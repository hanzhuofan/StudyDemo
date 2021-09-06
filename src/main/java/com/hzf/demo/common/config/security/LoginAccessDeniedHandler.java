package com.hzf.demo.common.config.security;

import com.hzf.demo.common.Result;
import com.hzf.demo.utils.JSON;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决认证过的用户访问无权限资源时的异常
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Component
public class LoginAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JSON.toJSONString(Result.of("权限不足:" + accessDeniedException.getMessage())));
    }
}
