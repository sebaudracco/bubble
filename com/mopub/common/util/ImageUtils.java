package com.mopub.common.util;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;

public class ImageUtils {
    @NonNull
    public static Bitmap applyFastGaussianBlurToBitmap(@NonNull Bitmap mutableBitmap, int radius) {
        int w = mutableBitmap.getWidth();
        int h = mutableBitmap.getHeight();
        int[] pixels = new int[(w * h)];
        mutableBitmap.getPixels(pixels, 0, w, 0, 0, w, h);
        for (int r = radius; r >= 1; r /= 2) {
            for (int i = r; i < h - r; i++) {
                for (int j = r; j < w - r; j++) {
                    int tl = pixels[(((i - r) * w) + j) - r];
                    int tr = pixels[(((i - r) * w) + j) + r];
                    int tc = pixels[((i - r) * w) + j];
                    int bl = pixels[(((i + r) * w) + j) - r];
                    int br = pixels[(((i + r) * w) + j) + r];
                    int bc = pixels[((i + r) * w) + j];
                    int cl = pixels[((i * w) + j) - r];
                    int cr = pixels[((i * w) + j) + r];
                    pixels[(i * w) + j] = ((-16777216 | ((((((((((tl & 255) + (tr & 255)) + (tc & 255)) + (bl & 255)) + (br & 255)) + (bc & 255)) + (cl & 255)) + (cr & 255)) >> 3) & 255)) | ((((((((((MotionEventCompat.ACTION_POINTER_INDEX_MASK & tl) + (MotionEventCompat.ACTION_POINTER_INDEX_MASK & tr)) + (MotionEventCompat.ACTION_POINTER_INDEX_MASK & tc)) + (MotionEventCompat.ACTION_POINTER_INDEX_MASK & bl)) + (MotionEventCompat.ACTION_POINTER_INDEX_MASK & br)) + (MotionEventCompat.ACTION_POINTER_INDEX_MASK & bc)) + (MotionEventCompat.ACTION_POINTER_INDEX_MASK & cl)) + (MotionEventCompat.ACTION_POINTER_INDEX_MASK & cr)) >> 3) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | ((((((((((16711680 & tl) + (16711680 & tr)) + (16711680 & tc)) + (16711680 & bl)) + (16711680 & br)) + (16711680 & bc)) + (16711680 & cl)) + (16711680 & cr)) >> 3) & 16711680);
                }
            }
        }
        mutableBitmap.setPixels(pixels, 0, w, 0, 0, w, h);
        return mutableBitmap;
    }
}
