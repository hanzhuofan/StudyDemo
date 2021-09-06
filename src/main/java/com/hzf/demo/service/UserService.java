package com.hzf.demo.service;

import com.hzf.demo.beans.domain.Menu;
import com.hzf.demo.beans.domain.Role;
import com.hzf.demo.beans.domain.User;
import com.hzf.demo.repository.MenuRepository;
import com.hzf.demo.repository.RoleRepository;
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
    MenuRepository menuRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("abc123"));
        userRepository.save(user);
        Menu menu = new Menu();
        menu.setId(1L);
        menu.setMethod("GET");
        menu.setUrl("/organization/test");
        menuRepository.save(menu);
        menu = new Menu();
        menu.setId(2L);
        menu.setMethod("POST");
        menu.setUrl("/organization/v1/save");
        menuRepository.save(menu);
        Role role = new Role();
        role.setId(1L);
        role.setName("管理员");
        role.getUsers().add(user);
        role.getMenus().addAll(menuRepository.findAll());
        roleRepository.save(role);
        user = new User();
        user.setId(2L);
        user.setUsername("zhuofan");
        user.setPassword(passwordEncoder.encode("abc123"));
        userRepository.save(user);
    }
}
