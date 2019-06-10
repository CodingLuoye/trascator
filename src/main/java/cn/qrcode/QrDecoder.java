package cn.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import org.apache.commons.lang3.StringUtils;


/**
 * @author YCKJ
 */
public class QrDecoder {
    private final MultiFormatReader multiFormatReader;
//    private final Hashtable<DecodeHintType, Object> hints;

    public QrDecoder() {
        multiFormatReader = new MultiFormatReader();
//        multiFormatReader.setHints(hints);
    }

    public void init() {
//        hints = new Hashtable<DecodeHintType, Object>(3);
//        hints.put(DecodeHintType.POSSIBLE_FORMATS, null);
//        hints.put(DecodeHintType.CHARACTER_SET, null);
//        hints.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, null);
    }


    /**
     * Decode the data within the viewfinder rectangle, and time how long it took. For efficiency,
     * reuse the same reader objects from one decode to the next.
     *
     * @param data   The YUV preview frame.
     * @param width  The width of the preview frame.
     * @param height The height of the preview frame.
     */
    public String decode(byte[] data, int width, int height) {
        long start = System.currentTimeMillis();
        Result rawResult = null;

        //modify here
        byte[] rotatedData = new byte[data.length];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedData[x * height + height - y - 1] = data[x + y * width];
            }
        }
        // Here we are swapping, that's the difference to #11
        int tmp = width;
        width = height;
        height = tmp;

        PlanarYUVLuminanceSource source = buildLuminanceSource(rotatedData, width, height);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            rawResult = multiFormatReader.decodeWithState(bitmap);
        } catch (ReaderException re) {
            // continue
        } finally {
            multiFormatReader.reset();
        }

        if (rawResult != null) {
            long end = System.currentTimeMillis();
            System.out.println("Found barcode" + (end - start) + " ms):\n" + rawResult.toString());
            System.out.println("Sending decode succeeded message...");
            handleDecode(rawResult);
            return rawResult.getText();
        } else {
            System.out.println("decode failed...");
        }
        return null;
    }


    /**
     * A factory method to build the appropriate LuminanceSource object based on the format
     * of the preview buffers, as described by Camera.Parameters.
     *
     * @param data   A preview frame.
     * @param width  The width of the image.
     * @param height The height of the image.
     * @return A PlanarYUVLuminanceSource instance.
     */
    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] data, int width, int height) {
        return new PlanarYUVLuminanceSource(data, width, height, 0, 0, width, height);

    }


    /**
     * Handler scan result
     *
     * @param result
     */
    public void handleDecode(Result result) {
        String resultString = result.getText();
        //FIXME
        if (StringUtils.isEmpty(resultString)) {
            System.out.println("Scan failed!");
        } else {
            System.out.println("sssssssssssssssss scan 0 = " + resultString);
            // 不能使用Intent传递大于40kb的bitmap，可以使用一个单例对象存储这个bitmap
//            bundle.putParcelable("bitmap", barcode);
//            Logger.d("saomiao",resultString);
        }
    }
}
