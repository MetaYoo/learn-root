package com.kotall.learn.jpa.dal.many2many;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStudentRepository extends JpaRepository<Student, Integer> {
}
