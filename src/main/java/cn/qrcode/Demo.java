package cn.qrcode;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author YCKJ
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        int width = 1280;
        int height = 720;
        QrDecoder qrDecoder = new QrDecoder();
        File file = new File("E:\\qrcode\\微信图片_20190517155737.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        inputStream.read(bytes);
        String decode = qrDecoder.decode(bytes, width, height);
        System.out.println(decode);
    }
}
