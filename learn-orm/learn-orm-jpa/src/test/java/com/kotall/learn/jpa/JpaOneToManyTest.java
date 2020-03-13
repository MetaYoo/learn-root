package com.kotall.learn.jpa;

import com.kotall.learn.jpa.dal.one2many.Dept;
import com.kotall.learn.jpa.dal.one2many.DeptRepository;
import com.kotall.learn.jpa.dal.one2many.Staff;
import com.kotall.learn.jpa.dal.one2many.StaffRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaOneToManyTest {

    @Autowired
    DeptRepository deptRepository;
    @Autowired
    StaffRepository staffRepository;

    @Test
    public void save() {
        Staff staff = new Staff();
        staff.setStaffName("于鲲");
        Dept dept = new Dept();
        dept.setDeptName("220实验室");
        staff.setDept(dept);
        dept.getStaffs().add(staff);
        deptRepository.save(dept);
    }

    @Test
    public void query() {
        Staff staff1 = staffRepository.getOne(1L);
        System.out.println(staff1);
    }

    @Test
    public void delete() {
        //staffRepository.deleteById(2L);
        deptRepository.deleteById(2L);
    }

}
