package com.hzf.demo.service;

import com.hzf.demo.beans.domain.User;
import com.hzf.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("abc123"));
        userRepository.save(user);
    }
}
