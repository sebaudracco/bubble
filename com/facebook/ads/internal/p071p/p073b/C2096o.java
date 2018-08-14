package com.facebook.ads.internal.p071p.p073b;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.File;

final class C2096o {
    public static File m6743a(Context context) {
        return new File(C2096o.m6744a(context, true), "video-cache");
    }

    private static File m6744a(Context context, boolean z) {
        File file = null;
        Object externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = "";
        }
        if (z && "mounted".equals(r1)) {
            file = C2096o.m6745b(context);
        }
        if (file == null) {
            file = context.getCacheDir();
        }
        if (file != null) {
            return file;
        }
        String str = "/data/data/" + context.getPackageName() + "/cache/";
        Log.w("ProxyCache", "Can't define system cache directory! '" + str + "%s' will be used.");
        return new File(str);
    }

    private static File m6745b(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "cache");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        Log.w("ProxyCache", "Unable to create external cache directory");
        return null;
    }
}
