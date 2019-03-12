package com.kotall.learn.objectcopy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/3/12 22:12
 */
public class ObjectDeepCopyTest {

    public static void main(String[] args) throws Exception {
        Age age = new Age(20);
        Person p1 = new Person(age, "p1");
        //通过序列化方法实现深拷贝
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(p1);
        oos.flush();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        Person p2 = (Person) ois.readObject();
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println();
        // 尝试修改stu1中的各属性，观察stu2的属性有没有变化
        p1.setName("大傻子");
        // 改变age这个引用类型的成员变量的值
        age.setAge(99);
        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }
}
