package com.hzf.demo.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzf.demo.repository.RoleDORepository;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Service
public class RoleService {
    @Autowired
    RoleDORepository roleDORepository;

    @PostConstruct
    public void init() {

    }
}
