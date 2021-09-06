package com.hzf.demo.common.config.security;

import com.hzf.demo.beans.LoginToken;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
public class LoginProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    public LoginProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        LoginToken loginToken = (LoginToken)authentication;
        defaultCheck(userDetails);

        additionalAuthenticationChecks(userDetails, loginToken);

        LoginToken authenticatedToken = new LoginToken(loginToken.getUser(), userDetails.getAuthorities());
        authenticatedToken.setDetails(loginToken.getDetails());
        return authenticatedToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return LoginToken.class.isAssignableFrom(authentication);
    }

    private void defaultCheck(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            throw new LockedException("User account is locked");
        }

        if (!user.isEnabled()) {
            throw new DisabledException("User is disabled");
        }

        if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException("User account has expired");
        }
    }

    private void additionalAuthenticationChecks(UserDetails userDetails, LoginToken authentication)
        throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("Bad credentials");
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
    }
}