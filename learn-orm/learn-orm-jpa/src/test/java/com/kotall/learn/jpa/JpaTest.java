package com.kotall.learn.jpa;

import com.kotall.learn.jpa.dal.many2many.JpaTeacherRepository;
import com.kotall.learn.jpa.dal.one2many.Dept;
import com.kotall.learn.jpa.dal.one2many.Staff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

    @PersistenceContext
    @Autowired
    private EntityManager em;

    @Autowired
    private JpaTeacherRepository jpaTeacherRepository;


    @Transactional
    @Test
    public void save() {
        Staff staff = new Staff();
        staff.setStaffName("于鲲");
        Dept dept = new Dept();
        dept.setDeptName("220实验室");
        staff.setDept(dept);
        dept.getStaffs().add(staff);
        //em.persist(dept);
        Query query = em.createNativeQuery("select * from t_dept where dept_id=1");
        Dept dept1 = (Dept) query.getSingleResult();
        System.out.println(dept1);

    }


}
