package com.hzf.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hzf.demo.beans.po.Department;

/**
 * @author zhuofan.han
 * @date 2021/9/2
 */
@Transactional
public interface DepartmentRepository extends JpaRepository<Department, Long> {}
