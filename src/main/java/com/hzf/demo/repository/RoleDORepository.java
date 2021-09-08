package com.hzf.demo.repository;

import com.hzf.demo.beans.domain.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author zhuofan.han
 * @date 2021/9/2
 */
@Transactional
public interface RoleDORepository extends JpaRepository<RoleDO, Long> {
    @Override
    List<RoleDO> findAll();
}
