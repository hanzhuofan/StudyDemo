package com.hzf.demo.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @author zhuofan.han
 * @date 2021/9/5
 */
public class LocaleInterceptor extends LocaleChangeInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String newLocale = request.getHeader(getParamName());
        if (newLocale != null) {
            if (checkHttpMethod(request.getMethod())) {
                LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
                if (localeResolver == null) {
                    throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
                }
                try {
                    localeResolver.setLocale(request, response, parseLocaleValue(newLocale));
                } catch (IllegalArgumentException ex) {
                    if (isIgnoreInvalidLocale()) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Ignoring invalid locale value [" + newLocale + "]: " + ex.getMessage());
                        }
                    } else {
                        throw ex;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkHttpMethod(String currentMethod) {
        String[] configuredMethods = getHttpMethods();
        if (ObjectUtils.isEmpty(configuredMethods)) {
            return true;
        }
        for (String configuredMethod : configuredMethods) {
            if (configuredMethod.equalsIgnoreCase(currentMethod)) {
                return true;
            }
        }
        return false;
    }
}
