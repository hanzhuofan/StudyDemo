package com.hzf.demo.common.filter;

import java.io.IOException;
import java.util.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzf.demo.utils.MessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hzf.demo.beans.dto.LoginUserDTO;
import com.hzf.demo.common.Constants;
import com.hzf.demo.common.Result;
import com.hzf.demo.common.config.security.LoginToken;
import com.hzf.demo.service.UserService;
import com.hzf.demo.utils.JsonUtils;
import com.hzf.demo.utils.TokenUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Slf4j
@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    UserService userService;

    private final List<RequestMatcher> matchers = new ArrayList<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
        String token = request.getHeader(Constants.TOKEN);
        for (RequestMatcher matcher : matchers) {
            if (matcher.matches(request)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        if (StringUtils.isBlank(token)) {
            response.setContentType(Constants.CONTENT_TYPE);
            response.getWriter().write(JsonUtils.toJsonString(Result.of("login.not")));
            return;
        }

        if (TokenUtils.verifyToken(token)) {
            response.setContentType(Constants.CONTENT_TYPE);
            response.getWriter().write(JsonUtils.toJsonString(Result.of("login.invalid")));
            return;
        }

        String user = TokenUtils.getUserString(token);
        if (!userService.isLogin(user)) {
            response.setContentType(Constants.CONTENT_TYPE);
            response.getWriter().write(JsonUtils.toJsonString(Result.of("login.invalid")));
            return;
        }
        LoginUserDTO loginUserDTO = userService.getLoginUser(user);
        LoginToken loginToken = new LoginToken(loginUserDTO, true);
        loginToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(loginToken);
        MessageUtils.setLocale(request.getHeader(Constants.LANGUAGE_PARAM_NAME));
        filterChain.doFilter(request, response);
    }

    public TokenFilter excludePathPatterns(String... antPatterns) {
        return excludePathPatterns(null, antPatterns);
    }

    public TokenFilter excludePathPatterns(HttpMethod httpMethod, String... antPatterns) {
        String method = (httpMethod != null) ? httpMethod.toString() : null;
        for (String pattern : antPatterns) {
            matchers.add(new AntPathRequestMatcher(pattern, method));
        }
        return this;
    }
}
