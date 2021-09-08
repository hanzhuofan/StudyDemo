package com.hzf.demo.common.config.security;

import com.hzf.demo.common.Constants;
import com.hzf.demo.common.Result;
import com.hzf.demo.common.ResultEnum;
import com.hzf.demo.utils.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhuofan.han
 * @date 2021/9/7
 */
@Component
public class LogoutResultHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(Constants.CONTENT_TYPE);
        response.getWriter().write(JSON.toJSONString(Result.of(ResultEnum.OUT)));
    }
}