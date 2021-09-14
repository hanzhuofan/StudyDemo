package com.hzf.demo.common.config.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.hzf.demo.common.Constants;
import com.hzf.demo.common.Result;
import com.hzf.demo.common.ResultEnum;
import com.hzf.demo.utils.JsonUtils;

/**
 * @author zhuofan.han
 * @date 2021/9/7
 */
@Component
public class LogoutResultHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException {
        response.setContentType(Constants.CONTENT_TYPE);
        response.getWriter().write(JsonUtils.toJsonString(Result.of(ResultEnum.OUT)));
    }
}
