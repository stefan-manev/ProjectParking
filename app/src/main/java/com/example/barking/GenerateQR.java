package com.example.barking;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class GenerateQR {

//    public String resNumber;

    public GenerateQR() {
//        this.resNumber = resNumber;
    }

    public Bitmap Generate(String resNumber){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Bitmap bitmap = Bitmap.createBitmap(250, 250, Bitmap.Config.RGB_565);
        Boolean flag = false;
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(resNumber, BarcodeFormat.QR_CODE, 250, 250);

            for (int x = 0; x < 250; x++){
                for (int y = 0; y < 250; y++){
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (flag)
            return bitmap;
        else
            return null;

    }

}
