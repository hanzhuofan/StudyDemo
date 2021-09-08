package com.hzf.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hzf.demo.beans.domain.RoleDO;

/**
 * @author zhuofan.han
 * @date 2021/9/2
 */
@Transactional
public interface RoleDORepository extends JpaRepository<RoleDO, Long> {
    @Override
    List<RoleDO> findAll();
}
