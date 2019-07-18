package com.kotall.learn.samples.vcard;

import com.beust.jcommander.internal.Maps;
import com.google.common.collect.Lists;
import ezvcard.parameter.AddressType;
import ezvcard.parameter.EmailType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.Address;
import ezvcard.property.Email;
import ezvcard.property.Gender;
import ezvcard.property.Telephone;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/6/29 17:20
 */
public class VCardTest {


    @Test
    public void testVCardKit() throws Exception {

        List<Address> addressList = Lists.newArrayList();

        // 家庭住址
        Address homeAddress = new Address();
        homeAddress.setCountry("中国");
        homeAddress.setRegion("襄阳市");
        homeAddress.setLocality("枣阳市");
        homeAddress.setLabel("1区");
        homeAddress.getTypes().add(AddressType.HOME);
        addressList.add(homeAddress);


        // 工作住址
        Address workAddress = new Address();
        workAddress.setCountry("中国");
        workAddress.setRegion("上海市");
        workAddress.setLocality("松江区");
        workAddress.setLabel("佘山镇");
        workAddress.getTypes().add(AddressType.WORK);
        addressList.add(workAddress);


        Map<TelephoneType, Telephone> phoneMaps = Maps.newHashMap();
        // 家庭电话
        //phoneMaps.put(TelephoneType.HOME, new Telephone("15001906957"));
        // 移动电话
        phoneMaps.put(TelephoneType.CELL, new Telephone("15001906957"));


        // 邮箱
        Map<EmailType, Email> emailMaps = Maps.newHashMap();
        emailMaps.put(EmailType.HOME, new Email("haiyasheji@163.com"));
        //emailMaps.put(EmailType.WORK, new Email("wangzhongping@ycmedia.cn"));

        String text = VCardKit.createVCardQRCode("王忠平", Gender.male(), phoneMaps, emailMaps, null, addressList);

        //VCardKit.genQrCode(text);


        // 生成带通讯录的二维码
        File qrcFile = new File("/data/", "mycard.jpg");
        VCardKit.writeToFile(text, "jpg", qrcFile, 1000, 1000);

        // 给通讯录二维码加上文字
        //VCardKit.pressText("Alpin", qrcFile, 5, Color.RED, 32);

        // 给通讯录二维码加上图片
        File logoFile = new File("/data/", "logo.png");
        File logoQrFile = new File("/data/", "qr_log.png");
        VCardKit.encodeWithLogo(qrcFile, logoFile, logoQrFile);

    }


    @Test
    public void testQrCode() throws Exception {
        File logoFile = new File("/data/", "logo.png");
        File logoQrFile = new File("/data/", "qr_log.png");
        VCardKit.encodeWithLogo(null, logoFile, logoQrFile);

    }

}
