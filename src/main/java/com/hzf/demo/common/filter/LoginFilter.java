package com.hzf.demo.common.filter;

import com.hzf.demo.beans.LoginToken;
import com.hzf.demo.beans.vo.LoginUserVO;
import com.hzf.demo.utils.JSON;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
        LoginUserVO loginUserVO;
        try (BufferedReader br = request.getReader()) {
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            loginUserVO = JSON.parseObject(sb.toString(), LoginUserVO.class);
        }
        LoginToken loginToken = new LoginToken(loginUserVO);
        loginToken.setDetails(new WebAuthenticationDetails(request));
        return this.getAuthenticationManager().authenticate(loginToken);
    }
}
