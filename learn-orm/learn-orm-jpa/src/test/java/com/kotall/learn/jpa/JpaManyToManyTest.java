package com.kotall.learn.jpa;

import com.kotall.learn.jpa.dal.many2many.JpaStudentRepository;
import com.kotall.learn.jpa.dal.many2many.JpaTeacherRepository;
import com.kotall.learn.jpa.dal.many2many.Student;
import com.kotall.learn.jpa.dal.many2many.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/13 14:14
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaManyToManyTest {

    @Autowired
    private JpaTeacherRepository jpaTeacherRepository;
    @Autowired
    private JpaStudentRepository jpaStudentRepository;

    @Test
    public void save() {
        Teacher t = new Teacher();
        t.setName("t1");

        Student s = new Student();
        s.setName("s1");

        s.getTeachers().add(t);
        t.getStudents().add(s);
        jpaTeacherRepository.save(t);
    }

    @Transactional
    @Test
    public void query() {
        Teacher teacher = jpaTeacherRepository.getOne(14);
        System.out.println(teacher.getStudents().size());
    }


    @Test
    public void queryWithSpecification() {
    }
}
