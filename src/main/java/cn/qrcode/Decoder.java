package cn.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.EnumMap;

/**
 * @author YCKJ
 */
public class Decoder {
    public static void main(String[] args) throws Exception {
//        String str = "E:\\qrcode\\2.jpg";
        String str = "E:\\qrcode\\1.jpg";
        File file = new File(str);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        inputStream.read(bytes);
        for (int i = 0; i < 100; i++) {
            long start = System.currentTimeMillis();
            String s = decodeQR(bytes);
            System.out.println(s);
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }

    /**
     * 解析二维码图片
     *
     * @param filePath 图片路径
     */
    public static String decodeQR(String filePath) {
        if ("".equalsIgnoreCase(filePath) && filePath.length() == 0) {
            return "二维码图片不存在!";
        }
        String content = "";
        EnumMap<DecodeHintType, Object> hints = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
        // 指定编码方式,防止中文乱码
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(filePath));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(binaryBitmap, hints);
            content = result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 解析二维码图片
     *
     * @param buf 图片路径
     */
    public static String decodeQR(byte[] buf) {
        InputStream inputStream = new ByteArrayInputStream(buf);
        String content = "";
        EnumMap<DecodeHintType, Object> hints = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
        // 指定编码方式,防止中文乱码
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BufferedImage image = ImageIO.read(inputStream);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(binaryBitmap, hints);
            content = result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
