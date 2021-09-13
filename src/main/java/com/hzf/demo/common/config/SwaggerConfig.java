package com.hzf.demo.common.config;

import com.hzf.demo.common.Constants;
import com.hzf.demo.common.config.properties.SwaggerProperties;
import com.hzf.demo.common.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

/**
 * @author zhuofan.han
 * @date 2021/9/12
 */
@Configuration
@Profile(value = {"dev"})
public class SwaggerConfig implements WebMvcConfigurer {
    @Autowired
    private SwaggerProperties swaggerProperties;

    @Autowired
    TokenFilter tokenFilter;

    @Bean
    public Docket createRestApi() {
        tokenFilter.excludePathPatterns(Constants.EXCLUDE).excludePathPatterns(HttpMethod.POST, "/login");
        return new Docket(DocumentationType.OAS_30).pathMapping("/").enable(swaggerProperties.getEnable())
            .apiInfo(apiInfo()).host(swaggerProperties.getTryHost()).select().apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any()).build().protocols(new HashSet<>(Arrays.asList(Constants.PROTOCOLS)))
            .securitySchemes(securitySchemes()).securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swaggerProperties.getApplicationName() + "Restful APIs")
            .description(swaggerProperties.getApplicationDescription()).termsOfServiceUrl("http://localhost:8082/")
            .contact(new Contact("zhuofan.han", null, "310756323@qq.com"))
            .version("Application Version: " + swaggerProperties.getApplicationVersion() + ", Spring Boot Version: "
                + SpringBootVersion.getVersion())
            .build();
    }

    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey(Constants.TOKEN, Constants.TOKEN,
            io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER.toString());
        return Collections.singletonList(apiKey);
    }

    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(SecurityContext.builder().securityReferences(Collections.singletonList(
            new SecurityReference(Constants.TOKEN, new AuthorizationScope[] {new AuthorizationScope("global", "")})))
            .build());
    }
}
