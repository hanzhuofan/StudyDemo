package com.hzf.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hzf.demo.beans.po.User;

/**
 * @author zhuofan.han
 * @date 2021/9/2 queryData.setHint(EntityGraph.EntityGraphType.LOAD.getKey(),
 *       entityManager.getEntityGraph("UserEntityGraph"));
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    @EntityGraph(value = "UserEntityGraph", type = EntityGraph.EntityGraphType.LOAD)
    List<User> findAll();

    @EntityGraph(value = "UserEntityGraph", type = EntityGraph.EntityGraphType.LOAD)
    User findByUsername(String username);
}
