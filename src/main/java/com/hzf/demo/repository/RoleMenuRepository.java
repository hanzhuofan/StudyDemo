package com.hzf.demo.repository;

import com.hzf.demo.beans.po.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author zhuofan.han
 * @date 2021/9/2
 */
@Transactional
public interface RoleMenuRepository extends JpaRepository<RoleMenu, RoleMenu.RoleMenuId> {
    List<RoleMenu> findByIdMenuId(long menuId);
}
