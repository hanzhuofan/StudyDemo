package com.hzf.demo.service;

import com.hzf.demo.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author zhuofan.han
 * @date 2021/9/6
 */
@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    @PostConstruct
    public void init() {

    }
}
