package com.hzf.demo.common.config.security;

import com.hzf.demo.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Component
public class RoleMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String requestUrl = filterInvocation.getRequestUrl();
        String method = filterInvocation.getHttpRequest().getMethod();
        List<BigInteger> authorities = menuRepository.findAuthorities(requestUrl, method);
        return SecurityConfig.createList(authorities.stream().map(BigInteger::toString).toArray(String[]::new));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
