package com.kotall.learn.samples.vcard;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.parameter.EmailType;
import ezvcard.parameter.ImageType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/6/29 20:35
 */
public class VCardKit {

    /**
     * 创建通讯录二维码
     *
     * @param name
     * @param gender
     * @param phoneMaps
     * @param emailMaps
     * @param photoUrl
     * @param addressList
     * @return
     */
    public static String createVCardQRCode(String name, Gender gender, Map<TelephoneType, Telephone> phoneMaps, Map<EmailType, Email> emailMaps, String photoUrl, List<Address> addressList) throws Exception {
        VCard vcard = new VCard();
        StructuredName n = new StructuredName();

        // 姓名
        vcard.setFormattedName(name);

        // 性别
        vcard.setGender(gender);

        // 电话号码
        phoneMaps.forEach((type, phone) -> {
            vcard.addTelephoneNumber(phone.getText(), type);
        });

        // 邮箱
        emailMaps.forEach((type, email) -> {
            vcard.addEmail(email.getValue(), type);
        });


        File file = new File("/data/portrait.jpg");
        Photo photo = new Photo(file, ImageType.JPEG);
        //vcard.addPhoto(photo);

        addressList.forEach(address -> {
            vcard.addAddress(address);
        });


        String text = Ezvcard.write(vcard).version(VCardVersion.V4_0).go();
        System.out.println(text);

        File vcardFile = new File("/data/vcard.vcf");
        Ezvcard.write(vcard).go(vcardFile);

        return text;

    }

    public static void genQrCode(String content) {
        try {

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            HashMap hints = new HashMap<>();
            //设置编码格式
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //设置纠错能力,纠错级别（L 7%、M 15%、Q 25%、H 30%）
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            //设置边距
            hints.put(EncodeHintType.MARGIN, 2);

            //生成二维码
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content.toString(), BarcodeFormat.QR_CODE, 300, 300, hints);

            File file = new File("/data/contacat_1.jpg");
            MatrixToImageWriter.writeToPath(bitMatrix, "jpg", file.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
