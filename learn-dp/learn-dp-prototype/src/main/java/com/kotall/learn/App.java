package com.kotall.learn;

import com.kotall.learn.prototype.JinGuBang;
import com.kotall.learn.prototype.SunWuKong;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {
        SunWuKong wuKong = new SunWuKong();
        wuKong.setAge(500);
        wuKong.setName("齐天大圣");
        wuKong.setBirthday(new Date());

        JinGuBang jinGuBang = new JinGuBang();
        jinGuBang.setLength(100);
        jinGuBang.setColor("GOLD");

        wuKong.setJinGuBang(jinGuBang);

        List<String> kongFu = new ArrayList<>();
        kongFu.add("腾云驾雾");
        kongFu.add("七十二变");

        wuKong.setKongFu(kongFu);

        SunWuKong copy = (SunWuKong) wuKong.clone();

        System.out.println("孙悟空本尊与副本是否是同一对象：" + (wuKong == copy));
        System.out.println("孙悟空本尊生日与副本生日是否是同一对象：" + (wuKong.getBirthday() == copy.getBirthday()));
        System.out.println("孙悟空本尊金箍棒与副本金箍棒是否是同一对象：" + (wuKong.getJinGuBang() == copy.getJinGuBang()));
        System.out.println("孙悟空本尊功夫绝技与副本功夫绝技是否是同一对象：" + (wuKong.getKongFu() == copy.getKongFu()));

    }
}
