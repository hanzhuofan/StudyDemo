package com.hzf.demo.repository;

import com.hzf.demo.beans.domain.UserDO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author zhuofan.han
 * @date 2021/9/2 queryData.setHint(EntityGraph.EntityGraphType.LOAD.getKey(),
 *       entityManager.getEntityGraph("UserEntityGraph"));
 */
@Transactional
public interface UserDORepository extends JpaRepository<UserDO, Long> {
    @Override
    @EntityGraph(value = "UserDOEntityGraph", type = EntityGraph.EntityGraphType.LOAD)
    List<UserDO> findAll();

    @EntityGraph(value = "UserDOEntityGraph", type = EntityGraph.EntityGraphType.LOAD)
    UserDO findByUsername(String username);
}
