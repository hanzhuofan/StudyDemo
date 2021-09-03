package com.hzf.demo.repository;

import com.hzf.demo.persistent.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhuofan.han
 * @date 2021/9/3
 */
public interface UserRoleRepository  extends JpaRepository<UserRole, UserRole.UserRoleId> {
    List<UserRole> findByIdUserId(long userId);
}
