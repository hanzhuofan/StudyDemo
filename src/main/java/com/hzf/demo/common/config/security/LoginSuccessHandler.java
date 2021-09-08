package com.hzf.demo.common.config.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.hzf.demo.beans.domain.RoleDO;
import com.hzf.demo.beans.dto.LoginUserDTO;
import com.hzf.demo.common.Constants;
import com.hzf.demo.common.Result;
import com.hzf.demo.service.UserService;
import com.hzf.demo.utils.JSON;
import com.hzf.demo.utils.TokenUtils;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException {
        response.setContentType(Constants.CONTENT_TYPE);
        LoginToken loginToken = (LoginToken)authentication;
        LoginUserDTO loginUserDTO = loginToken.getPrincipal();
        for (GrantedAuthority authority : loginToken.getAuthorities()) {
            loginUserDTO.getAuthorities().add((RoleDO)authority);
        }
        userService.login(loginUserDTO);
        String token = TokenUtils.createToken(loginUserDTO.getUsername());
        response.getWriter().write(JSON.toJSONString(Result.ok(token)));
    }
}
