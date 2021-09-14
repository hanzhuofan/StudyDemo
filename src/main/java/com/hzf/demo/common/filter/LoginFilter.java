package com.hzf.demo.common.filter;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzf.demo.common.validation.ValidatorHelper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.hzf.demo.beans.vo.LoginUserVO;
import com.hzf.demo.common.config.security.LoginToken;
import com.hzf.demo.convert.UserConvert;
import com.hzf.demo.utils.JsonUtils;

import lombok.SneakyThrows;

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
            loginUserVO = JsonUtils.parseObject(sb.toString(), LoginUserVO.class);
            ValidatorHelper.valid(loginUserVO);
            LoginToken loginToken = new LoginToken(UserConvert.INSTANCE.vo2dto(loginUserVO), false);
            loginToken.setDetails(new WebAuthenticationDetails(request));
            return this.getAuthenticationManager().authenticate(loginToken);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
    }
}
