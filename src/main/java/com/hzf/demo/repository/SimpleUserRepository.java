package com.hzf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hzf.demo.beans.po.SimpleUser;

/**
 * @author zhuofan.han
 * @date 2021/9/2
 */
public interface SimpleUserRepository extends JpaRepository<SimpleUser, Long> {}
