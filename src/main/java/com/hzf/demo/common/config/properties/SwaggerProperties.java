package com.hzf.demo.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhuofan.han
 * @date 2021/9/12
 */
@Data
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {
    private Boolean enable;

    private String applicationName;

    private String applicationVersion;

    private String applicationDescription;

    private String tryHost;
}
