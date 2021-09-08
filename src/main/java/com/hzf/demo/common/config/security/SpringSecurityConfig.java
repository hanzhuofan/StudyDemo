package com.hzf.demo.common.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.filter.CorsFilter;

import com.hzf.demo.common.filter.LoginFilter;
import com.hzf.demo.common.filter.OptionsRequestFilter;
import com.hzf.demo.common.filter.TokenFilter;

import lombok.RequiredArgsConstructor;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    TokenFilter tokenFilter;
    @Autowired
    OptionsRequestFilter optionsRequestFilter;
    @Autowired
    LogoutHandler logoutHandler;
    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
    @Autowired
    private AccessDecisionManager accessDecisionManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LoginProvider loginProvider = new LoginProvider(userDetailsService, passwordEncoder());

        http.authenticationProvider(loginProvider).authorizeRequests()
            .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                @Override
                public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                    object.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
                    object.setAccessDecisionManager(accessDecisionManager);
                    return object;
                }
            }).anyRequest().authenticated().and().formLogin().and().logout().addLogoutHandler(logoutHandler)
            .logoutSuccessHandler(logoutSuccessHandler);

        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        loginFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        http.sessionManagement().disable().cors().disable().csrf().disable()
            .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(tokenFilter, LoginFilter.class).addFilterAfter(optionsRequestFilter, CorsFilter.class);

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(authenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().mvcMatchers("/static/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
