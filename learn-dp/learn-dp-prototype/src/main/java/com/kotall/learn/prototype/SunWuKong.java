package com.kotall.learn.prototype;

import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 1. 克隆是不走构造方法的
 * 2. java中默认潜克隆 基本类型和String类型在调用super.clone()时会克隆，其他类型不会
 *
 * @author zpwang
 * @version 1.0.0
 */
@Data
public class SunWuKong extends Monkey implements Cloneable, Serializable {

    private static final long serialVersionUID = 2243988872760362941L;
    private JinGuBang jinGuBang;

    private List<String> kongFu;

    @Override
    public Object clone() throws CloneNotSupportedException {
        //SunWuKong wuKong = (SunWuKong) super.clone();
        //wuKong.setKongFu((ArrayList<String>)((ArrayList<String>)kongFu).clone());
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;

        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            // 序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            // 反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);

            SunWuKong copy = (SunWuKong) ois.readObject();

            copy.setBirthday(new Date());
            return copy;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                ois.close();
                bis.close();
                oos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
