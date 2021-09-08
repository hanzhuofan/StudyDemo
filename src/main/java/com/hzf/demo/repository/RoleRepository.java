package com.hzf.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hzf.demo.beans.po.Role;

/**
 * @author zhuofan.han
 * @date 2021/9/2
 */
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Override
    @EntityGraph(value = "RoleEntityGraph", type = EntityGraph.EntityGraphType.LOAD)
    List<Role> findAll();
}
