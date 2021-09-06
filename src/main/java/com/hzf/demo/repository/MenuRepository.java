package com.hzf.demo.repository;

import com.hzf.demo.beans.domain.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigInteger;
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

    @Query("select rm.id.roleId from Menu m, RoleMenu rm where m.id = rm.id.menuId and m.url = ?1 and m.method = ?2")
    List<BigInteger> findAuthorities(String url, String method);
}
