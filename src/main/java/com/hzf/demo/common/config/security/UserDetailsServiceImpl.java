package com.hzf.demo.common.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hzf.demo.beans.domain.UserDO;
import com.hzf.demo.repository.UserDORepository;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDORepository userDORepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userDORepository.findByUsername(username);
        if (userDO == null) {
            throw new UsernameNotFoundException(username);
        }
        return userDO;
    }
}