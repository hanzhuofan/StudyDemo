package com.hzf.demo.common.config.security;

import com.hzf.demo.beans.domain.Role;
import com.hzf.demo.beans.dto.LoginUserDTO;
import com.hzf.demo.common.Constants;
import com.hzf.demo.common.Result;
import com.hzf.demo.service.UserService;
import com.hzf.demo.utils.JSON;
import com.hzf.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        LoginUserDTO loginUserDTO = loginToken.getUser();
        for (GrantedAuthority authority : loginToken.getAuthorities()) {
            loginUserDTO.getAuthorities().add((Role)authority);
        }
        userService.login(loginUserDTO);
        String token = TokenUtils.createToken(JSON.toJSONString(loginUserDTO));
        response.getWriter().write(JSON.toJSONString(Result.ok(token)));
    }
}
