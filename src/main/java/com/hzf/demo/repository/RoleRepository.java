package com.hzf.demo.repository;

import com.hzf.demo.beans.po.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

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
