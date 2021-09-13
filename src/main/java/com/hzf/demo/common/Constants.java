package com.hzf.demo.common;

/**
 * @author zhuofan.han
 * @date 2021/9/5
 */
public interface Constants {
    String LANGUAGE_PARAM_NAME = "lang";
    String CONTENT_TYPE = "application/json;charset=UTF-8";
    String TOKEN = "token";
    String[] EXCLUDE = new String[] {"/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources",
        "/swagger-resources/configuration/security", "/swagger-ui.html", "/swagger-ui/*", "/v3/**", "/webjars/**",
        "/swagger**/**", "/doc.html", "/swagger-ui*"};
    String[] PROTOCOLS = new String[] {"https", "http"};
}
