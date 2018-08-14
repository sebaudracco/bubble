package com.google.android.gms.internal.vision;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public final class zzm {
    public static Bitmap zzb(Bitmap bitmap, zzk com_google_android_gms_internal_vision_zzk) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (com_google_android_gms_internal_vision_zzk.rotation != 0) {
            int i;
            Matrix matrix = new Matrix();
            switch (com_google_android_gms_internal_vision_zzk.rotation) {
                case 0:
                    i = 0;
                    break;
                case 1:
                    i = 90;
                    break;
                case 2:
                    i = 180;
                    break;
                case 3:
                    i = 270;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported rotation degree.");
            }
            matrix.postRotate((float) i);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        }
        if (com_google_android_gms_internal_vision_zzk.rotation == 1 || com_google_android_gms_internal_vision_zzk.rotation == 3) {
            com_google_android_gms_internal_vision_zzk.width = height;
            com_google_android_gms_internal_vision_zzk.height = width;
        }
        return bitmap;
    }
}
