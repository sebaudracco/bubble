package com.facebook.ads.internal.p056q.p075b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.WorkerThread;
import android.util.Base64;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class C2137c {
    private static int m6842a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i3 /= 2;
            i4 /= 2;
            while (i3 / i5 >= i2 && i4 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static Bitmap m6843a(C2136b c2136b) {
        byte[] decode = Base64.decode(c2136b.m6841a(C2133v.f5083b), 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    @WorkerThread
    public static Bitmap m6844a(InputStream inputStream, int i, int i2) {
        InputStream bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedInputStream.mark(8192);
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(bufferedInputStream, null, options);
        try {
            bufferedInputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        options.inSampleSize = C2137c.m6842a(options, i2, i);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(bufferedInputStream, null, options);
    }

    @WorkerThread
    public static Bitmap m6845a(String str, int i, int i2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = C2137c.m6842a(options, i2, i);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static Drawable m6846a(Context context, C2136b c2136b) {
        return new BitmapDrawable(context.getResources(), C2137c.m6843a(c2136b));
    }
}
