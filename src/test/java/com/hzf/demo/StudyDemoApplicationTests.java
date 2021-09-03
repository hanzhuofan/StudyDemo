package com.hzf.demo;

import com.hzf.demo.domain.*;
import com.hzf.demo.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;

@SpringBootTest
class StudyDemoApplicationTests {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SimpleUserRepository simpleUserRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void contextLoads() {
//        Role role = new Role();
//        role.setName("admin");
//        role = roleRepository.save(role);
//
//        User user = new User();
//        user.setUsername("zhuofan");
//        user.setPassword("test1234");
//        user.getAuthorities().add(role);
//        userRepository.save(user);
//
//        Menu menu = new Menu();
//        menu.setName("test");
//        menu.setUrl("/test/url");
//        menu.getAuthorities().add(role);
//        menuRepository.save(menu);
//
//        role = new Role();
//        role.setName("user");
//        role = roleRepository.save(role);
//
//        user = new User();
//        user.setUsername("zhuofan123");
//        user.setPassword("test1234");
//        user.getAuthorities().add(role);
//        userRepository.save(user);
//
//        menu = new Menu();
//        menu.setName("test123");
//        menu.setUrl("/test/url");
//        menu.getAuthorities().add(role);
//        menuRepository.save(menu);
//
//        for (User user1 : userRepository.findAll()) {
//            System.out.println(user1.getAuthorities());
//            for (Role authority : user1.getAuthorities()) {
//                System.out.println(authority.getMenus());
//            }
//
//        }

//        userRepository.findAll().forEach(System.out::println);
//        roleRepository.findAll().forEach(System.out::println);
//        menuRepository.findAll().forEach(System.out::println);
//        userRoleRepository.findAll().forEach(System.out::println);

        Department root = new Department();
        root.setName("root");
        departmentRepository.save(root);
        Department child1 = new Department();
        child1.setName("child1");
        child1.setParentId(root.getId());
        departmentRepository.save(child1);
        Department child2 = new Department();
        child2.setName("child2");
        child2.setParentId(root.getId());
        departmentRepository.save(child2);
        Department child3 = new Department();
        child3.setName("child3");
        child3.setParentId(root.getId());
        departmentRepository.save(child3);

        Department child11 = new Department();
        child11.setName("child11");
        child11.setParentId(child1.getId());
        departmentRepository.save(child11);

        for (Department department : departmentRepository.findAll()) {
            System.out.println(department);
        }

    }
}