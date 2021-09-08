package com.hzf.demo.common.config.security;

import com.hzf.demo.common.Constants;
import com.hzf.demo.service.UserService;
import com.hzf.demo.utils.TokenUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuofan.han
 * @date 2021/9/7
 */
@Component
public class Logout2Handler implements LogoutHandler {
    @Autowired
    UserService userService;

    @SneakyThrows
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader(Constants.TOKEN);
        if (StringUtils.isBlank(token) || !TokenUtils.verifyToken(token)) {
            return;
        }
        String user = TokenUtils.getUserString(token);
        userService.logout(user);
    }
}
