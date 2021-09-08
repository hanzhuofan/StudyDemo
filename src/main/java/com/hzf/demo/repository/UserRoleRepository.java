package com.hzf.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hzf.demo.beans.po.UserRole;

/**
 * @author zhuofan.han
 * @date 2021/9/3
 */
public interface UserRoleRepository extends JpaRepository<UserRole, UserRole.UserRoleId> {
    List<UserRole> findByIdUserId(long userId);
}
