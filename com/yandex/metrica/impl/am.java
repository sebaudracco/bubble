package com.yandex.metrica.impl;

import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Locale;

public final class am {

    public static final class C4324a {
        private static final String[] f11642a = new String[]{"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};

        public static boolean m14593a() {
            try {
                if (new File("/system/app/Superuser.apk").exists()) {
                    return true;
                }
            } catch (Throwable th) {
            }
            return false;
        }

        public static boolean m14594b() {
            String[] strArr = f11642a;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                try {
                    if (new File(strArr[i] + "su").exists()) {
                        return true;
                    }
                    i++;
                } catch (Throwable th) {
                }
            }
            return false;
        }

        public static int m14595c() {
            return (C4324a.m14593a() || C4324a.m14594b()) ? 1 : 0;
        }
    }

    public static Point m14597a(Context context) {
        int i;
        int i2;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
        } else if (VERSION.SDK_INT >= 14) {
            try {
                Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                i = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i2 = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception e) {
                i = defaultDisplay.getWidth();
                i2 = defaultDisplay.getHeight();
            }
        } else {
            i = defaultDisplay.getWidth();
            i2 = defaultDisplay.getHeight();
        }
        return new Point(i, i2);
    }

    public static String m14599b(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        Object country = locale.getCountry();
        StringBuilder stringBuilder = new StringBuilder(language);
        if (bk.m14985a(21)) {
            Object script = locale.getScript();
            if (!TextUtils.isEmpty(script)) {
                stringBuilder.append('-').append(script);
            }
        }
        if (!TextUtils.isEmpty(country)) {
            stringBuilder.append('_').append(country);
        }
        return stringBuilder.toString();
    }

    public static long m14596a(boolean z) {
        try {
            StatFs b = m14598b(z);
            return (((long) b.getBlockSize()) * ((long) b.getBlockCount())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        } catch (Throwable th) {
            return 0;
        }
    }

    public static StatFs m14598b(boolean z) {
        if (z) {
            return new StatFs(Environment.getRootDirectory().getAbsolutePath());
        }
        return new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    public static long m14600c(boolean z) {
        try {
            StatFs b = m14598b(z);
            return (((long) b.getBlockSize()) * ((long) b.getAvailableBlocks())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        } catch (Throwable th) {
            return 0;
        }
    }
}
