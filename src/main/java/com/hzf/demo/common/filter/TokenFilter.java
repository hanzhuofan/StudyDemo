package com.hzf.demo.common.filter;

import com.hzf.demo.beans.dto.LoginUserDTO;
import com.hzf.demo.common.Constants;
import com.hzf.demo.common.Result;
import com.hzf.demo.common.config.security.LoginToken;
import com.hzf.demo.service.UserService;
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
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Slf4j
@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String token = request.getHeader(Constants.TOKEN);
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
        if (!userService.isLogin(user)) {
            response.setContentType(Constants.CONTENT_TYPE);
            response.getWriter().write(JSON.toJSONString(Result.of("login.invalid")));
            return;
        }
        LoginToken loginToken = new LoginToken(user, user.getAuthorities());
        loginToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(loginToken);

        CustomHttpServletRequest request2 = new CustomHttpServletRequest(request);
        request2.addHeader(Constants.LANGUAGE_PARAM_NAME, user.getLang());
        filterChain.doFilter(request2, response);
    }

    public static class CustomHttpServletRequest extends HttpServletRequestWrapper {
        private final Map<String, String> headers = new HashMap<>();

        public CustomHttpServletRequest(HttpServletRequest request) {
            super(request);
        }

        public void addHeader(String name, String value) {
            headers.put(name, value);
        }

        @Override
        public String getHeader(String name) {
            String value = super.getHeader(name);
            if (headers.containsKey(name)) {
                value = headers.get(name);
            }
            return value;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            List<String> names = Collections.list(super.getHeaderNames());
            names.addAll(headers.keySet());
            return Collections.enumeration(names);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            List<String> list = Collections.list(super.getHeaders(name));
            if (headers.containsKey(name)) {
                list.add(headers.get(name));
            }
            return Collections.enumeration(list);
        }
    }
}
