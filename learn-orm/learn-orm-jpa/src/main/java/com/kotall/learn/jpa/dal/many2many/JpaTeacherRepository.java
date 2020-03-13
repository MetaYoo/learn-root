package com.kotall.learn.jpa.dal.many2many;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaTeacherRepository extends JpaRepository<Teacher, Integer>, JpaSpecificationExecutor<Teacher> {
}
