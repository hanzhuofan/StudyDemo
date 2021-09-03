package com.hzf.demo.repository;

import com.hzf.demo.persistent.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhuofan.han
 * @date 2021/9/2
 */
public interface SimpleUserRepository extends JpaRepository<SimpleUser, Long> {}
