package com.hzf.demo.repository;

import com.hzf.demo.beans.po.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * @author zhuofan.han
 * @date 2021/9/2
 */
@Transactional
public interface DepartmentRepository extends JpaRepository<Department, Long> {}
