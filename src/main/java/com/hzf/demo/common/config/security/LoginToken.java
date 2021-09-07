package com.hzf.demo.common.config.security;

import com.hzf.demo.beans.dto.LoginUserDTO;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
public class LoginToken extends AbstractAuthenticationToken {
    private final LoginUserDTO user;

    public LoginToken(LoginUserDTO user) {
        super(null);
        this.user = user;
        setAuthenticated(false);
    }

    public LoginToken(LoginUserDTO user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.user = user;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getPrincipal() {
        return user.getUsername();
    }

    public String getLang() {
        return user.getLang();
    }

    public LoginUserDTO getUser() {
        return user;
    }
}
