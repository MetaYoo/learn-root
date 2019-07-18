package com.kotall.learn.samples.vcard;

import com.beust.jcommander.internal.Maps;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.parameter.EmailType;
import ezvcard.parameter.ImageType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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


    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

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


        //File file = new File("/data/portrait.jpg");
        //Photo photo = new Photo(file, ImageType.JPEG);
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


    /**
     * 把生成的二维码存入到图片中
     *
     * @param matrix zxing包下的二维码类
     * @return
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    /**
     * 生成二维码并写入文件
     *
     * @param content 扫描二维码的内容
     * @param format  图片格式 jpg
     * @param file    文件
     * @param width    图片宽度
     * @param height    图片高度
     * @throws Exception
     */
    public static void writeToFile(String content, String format, File file, int width, int height)
            throws Exception {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = Maps.newHashMap();
        // 设置UTF-8， 防止中文乱码
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 设置二维码四周白色区域的大小
        hints.put(EncodeHintType.MARGIN, 1);
        // 设置二维码的容错性
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 画二维码
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = toBufferedImage(bitMatrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }


    /**
     * 给二维码图片加上文字
     *
     * @param pressText 文字
     * @param qrFile    二维码文件
     * @param fontStyle
     * @param color
     * @param fontSize
     */
    public static void pressText(String pressText, File qrFile, int fontStyle, Color color, int fontSize) throws Exception {
        pressText = new String(pressText.getBytes(), "utf-8");
        Image src = ImageIO.read(qrFile);
        int imageW = src.getWidth(null);
        int imageH = src.getHeight(null);
        BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        g.drawImage(src, 0, 0, imageW, imageH, null);
        //设置画笔的颜色
        g.setColor(color);
        //设置字体
        Font font = new Font("宋体", fontStyle, fontSize);
        FontMetrics metrics = g.getFontMetrics(font);
        //文字在图片中的坐标 这里设置在中间
        int startX = (300 - metrics.stringWidth(pressText)) / 2;
        int startY = 300 / 2;
        g.setFont(font);
        g.drawString(pressText, startX, startY);
        g.dispose();
        FileOutputStream out = new FileOutputStream(qrFile);
        ImageIO.write(image, "JPEG", out);
        out.close();
        System.out.println("image press success");
    }


    private static final String CHARSET = "UTF-8";

    public static File encode(String contents, int width, int height, File qrFile) {
        //生成条形码时的一些配置
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);

        BitMatrix bitMatrix;
        try {
            OutputStream out = new FileOutputStream(qrFile);
            // 生成二维码
            bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrFile;
    }

    public static File encodeWithLogo(File qrFile, File logoFile, File newQrFile) {
        OutputStream os = null;
        try {
            Image image2 = ImageIO.read(qrFile);
            int width = image2.getWidth(null);
            int height = image2.getHeight(null);
            BufferedImage bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //BufferedImage bufferImage =ImageIO.read(image) ;
            Graphics2D g2 = bufferImage.createGraphics();
            g2.drawImage(image2, 0, 0, width, height, null);
            int matrixWidth = bufferImage.getWidth();
            int matrixHeigh = bufferImage.getHeight();

            //读取Logo图片
            BufferedImage logo = ImageIO.read(logoFile);
            //开始绘制图片
            g2.drawImage(logo, matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, null);//绘制
            BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            g2.setStroke(stroke);// 设置笔画对象
            //指定弧度的圆角矩形
            RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, 20, 20);
            g2.setColor(Color.white);
            g2.draw(round);// 绘制圆弧矩形

            //设置logo 有一道灰色边框
            BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            g2.setStroke(stroke2);// 设置笔画对象
            RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth / 5 * 2 + 2, matrixHeigh / 5 * 2 + 2, matrixWidth / 5 - 4, matrixHeigh / 5 - 4, 20, 20);
            g2.setColor(new Color(128, 128, 128));
            g2.draw(round2);// 绘制圆弧矩形

            g2.dispose();

            bufferImage.flush();
            os = new FileOutputStream(newQrFile);
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
            en.encode(bufferImage);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return newQrFile;
    }

    /**
     * ZXing解码
     *
     * @param qrFile
     * @return
     */
    public String decode(File qrFile) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(qrFile);
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, CHARSET);

            result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getText();
    }


    public String createLogo() {


        return "";
    }

}
