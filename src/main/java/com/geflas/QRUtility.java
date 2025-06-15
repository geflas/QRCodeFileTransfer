package com.geflas;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.EncodeHintType;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRUtility {
    public static void generateQRCodeImage(String text, String filePath, int width, int height)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType,Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        BitMatrix bitMatrix = qrCodeWriter.encode(text,BarcodeFormat.QR_CODE,width,height,hints);
        MatrixToImageWriter.writeToPath(bitMatrix,"png", Paths.get(filePath));

    }
}
