package com.hzf.demo.common.config.security;

import com.hzf.demo.beans.domain.UserDO;
import com.hzf.demo.beans.dto.LoginUserDTO;
import com.hzf.demo.convert.UserConvert;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
public class LoginToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = -2008505237738496694L;

    private final LoginUserDTO user;

    public LoginToken(LoginUserDTO user, Boolean authenticated) {
        super(user.getAuthorities());
        this.user = user;
        setAuthenticated(authenticated);
    }

    public LoginToken(UserDO userDO, String lang) {
        super(userDO.getAuthorities());
        this.user = UserConvert.INSTANCE.do2dto(userDO);
        this.user.setLang(lang);
        super.setAuthenticated(true);
    }

    @Override
    public String getCredentials() {
        return user.getPassword();
    }

    @Override
    public LoginUserDTO getPrincipal() {
        return user;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
