package com.hzf.demo.repository;

import com.hzf.demo.domain.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author zhuofan.han
 * @date 2021/9/2
 */
@Transactional
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Override
    @EntityGraph(value = "MenuEntityGraph", type = EntityGraph.EntityGraphType.LOAD)
    List<Menu> findAll();
}
