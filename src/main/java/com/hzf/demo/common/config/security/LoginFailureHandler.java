package com.hzf.demo.common.config.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.hzf.demo.common.Constants;
import com.hzf.demo.common.Result;
import com.hzf.demo.utils.JSON;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException {
        String msg = "login.fail";
        Object[] args = null;
        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
            msg = "login.error";
        } else if (exception instanceof LockedException) {
            msg = "login.lock";
        } else if (exception instanceof CredentialsExpiredException) {
            msg = "login.expired.password";
        } else if (exception instanceof AccountExpiredException) {
            msg = "login.expired.account";
        } else if (exception instanceof DisabledException) {
            msg = "login.disabled";
        } else if (exception instanceof AuthenticationServiceException) {
            msg = "login.auth.exception";
            args = new Object[]{exception.getMessage()};
        }

        response.setContentType(Constants.CONTENT_TYPE);
        response.getWriter().write(JSON.toJSONString(Result.of(msg, args)));
    }
}
