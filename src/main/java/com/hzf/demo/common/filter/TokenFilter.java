package com.hzf.demo.common.filter;

import com.hzf.demo.beans.LoginToken;
import com.hzf.demo.beans.dto.LoginUserDTO;
import com.hzf.demo.common.Constants;
import com.hzf.demo.common.Result;
import com.hzf.demo.convert.UserConvert;
import com.hzf.demo.utils.JSON;
import com.hzf.demo.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Slf4j
@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    UserConvert userConvert;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            response.setContentType(Constants.CONTENT_TYPE);
            response.getWriter().write(JSON.toJSONString(Result.of("login.not")));
            return;
        }

        if (!TokenUtils.verifyToken(token)) {
            response.setContentType(Constants.CONTENT_TYPE);
            response.getWriter().write(JSON.toJSONString(Result.of("login.invalid")));
            return;
        }

        LoginUserDTO user = JSON.parseObject(TokenUtils.getUserString(token), LoginUserDTO.class);
        LoginToken loginToken = new LoginToken(userConvert.dto2vo(user), user.getAuthorities());
        loginToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(loginToken);

        CustomHttpServletRequest request2 = new CustomHttpServletRequest(request);
        request2.addHeader(Constants.LANGUAGE_PARAM_NAME, user.getLang());
        filterChain.doFilter(request2, response);
    }
}
