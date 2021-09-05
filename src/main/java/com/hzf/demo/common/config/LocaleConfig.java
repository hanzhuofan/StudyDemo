package com.hzf.demo.common.config;

import com.hzf.demo.common.Constants;
import com.hzf.demo.common.interceptor.LocaleInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author zhuofan.han
 * @date 2021/9/5
 */
@Configuration
@RequiredArgsConstructor
public class LocaleConfig implements WebMvcConfigurer {
    private final LocaleChangeInterceptor localeChangeInterceptor;

    private final ResourceBundleMessageSource resourceBundleMessageSource;

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor i18nInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleInterceptor();
        localeChangeInterceptor.setParamName(Constants.LANGUAGE_PARAM_NAME);
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory();
        localValidatorFactoryBean.setMessageInterpolator(interpolatorFactory.getObject());
        localValidatorFactoryBean.getValidationPropertyMap().put("hibernate.validator.fail_fast", "true");
        localValidatorFactoryBean.setValidationMessageSource(resourceBundleMessageSource);
        return localValidatorFactoryBean;
    }
}
