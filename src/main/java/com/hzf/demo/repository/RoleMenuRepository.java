package com.hzf.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hzf.demo.beans.po.RoleMenu;

/**
 * @author zhuofan.han
 * @date 2021/9/2
 */
@Transactional
public interface RoleMenuRepository extends JpaRepository<RoleMenu, RoleMenu.RoleMenuId> {
    List<RoleMenu> findByIdMenuId(long menuId);
}
