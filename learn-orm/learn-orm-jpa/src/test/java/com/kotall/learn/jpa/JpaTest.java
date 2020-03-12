package com.kotall.learn.jpa;

import com.kotall.learn.jpa.dal.many2many.JpaTeacherRepository;
import com.kotall.learn.jpa.dal.many2many.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

    @Autowired
    JpaTeacherRepository jpaTeacherRepository;

    @Test
    public void save() {
        Teacher teacher = new Teacher();
        teacher.setName("t1");
        this.jpaTeacherRepository.save(teacher);
    }
}
