package com.facebook.ads.internal.p066i;

import android.content.Context;
import android.util.Log;
import com.facebook.ads.internal.p063f.C1980c;
import com.facebook.ads.internal.p068l.C2005a;

public class C1992a {
    private static final String f4672a = C1992a.class.getName();
    private static C1992a f4673b;
    private static boolean f4674c = false;
    private Context f4675d;

    private C1992a(Context context) {
        this.f4675d = context;
    }

    public static C1992a m6302a(Context context) {
        if (f4673b == null) {
            Context applicationContext = context.getApplicationContext();
            synchronized (applicationContext) {
                if (f4673b == null) {
                    f4673b = new C1992a(applicationContext);
                }
            }
        }
        return f4673b;
    }

    public synchronized void m6303a() {
        if (!f4674c) {
            if (C2005a.m6346g(this.f4675d)) {
                try {
                    Thread.setDefaultUncaughtExceptionHandler(new C1980c(Thread.getDefaultUncaughtExceptionHandler(), this.f4675d, new C1995c(this.f4675d, false).m6315b()));
                } catch (Throwable e) {
                    Log.e(f4672a, "No permissions to set the default uncaught exception handler", e);
                }
            }
            f4674c = true;
        }
    }
}
