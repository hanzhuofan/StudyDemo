package com.hzf.demo.common.config.security;

import com.hzf.demo.beans.domain.Menu;
import com.hzf.demo.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

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

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> allMenu = menuRepository.findAll();
        for (Menu menu : allMenu) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl) && menu.getAuthorities().size() > 0) {
                String[] roleIds = menu.getAuthorities().stream().map(r -> r.getId().toString()).toArray(String[]::new);
                return SecurityConfig.createList(roleIds);
            }
        }
        // TODO
        return SecurityConfig.createList();
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
