package com.facebook.ads.internal.p056q.p078e;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.util.Log;
import com.facebook.ads.internal.p056q.p077d.C2150a;

public class C2151a {
    private static final String f5137a = C2151a.class.getSimpleName();

    public static boolean m6889a(Context context) {
        return C2151a.m6890b(context) && C2152b.m6892b(context);
    }

    public static boolean m6890b(Context context) {
        if (context == null) {
            Log.v(f5137a, "Invalid context in screen interactive check, assuming interactive.");
            return true;
        }
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            return VERSION.SDK_INT >= 20 ? powerManager.isInteractive() : powerManager.isScreenOn();
        } catch (Throwable e) {
            Log.e(f5137a, "Exception in screen interactive check, assuming interactive.", e);
            C2150a.m6888a(e, context);
            return true;
        }
    }
}
