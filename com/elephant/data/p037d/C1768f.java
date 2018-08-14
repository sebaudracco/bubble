package com.elephant.data.p037d;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.elephant.data.p048g.C1814b;
import java.util.concurrent.ArrayBlockingQueue;

public final class C1768f {
    private static ArrayBlockingQueue f3695a = new ArrayBlockingQueue(50);
    private static C1773l f3696b;
    private static Handler f3697c = new Handler(Looper.getMainLooper());
    private static boolean f3698d = false;

    static {
        String str = C1814b.hA;
    }

    private static synchronized void m5113a(Context context) {
        synchronized (C1768f.class) {
            if (context != null) {
                if (!f3698d) {
                    Context applicationContext = context.getApplicationContext();
                    if (f3696b == null) {
                        Runnable c1773l = new C1773l(f3695a, applicationContext);
                        f3696b = c1773l;
                        new Thread(c1773l).start();
                    }
                    f3698d = true;
                }
            }
        }
    }

    public static void m5114a(Context context, C1769g c1769g) {
        if (!f3698d) {
            C1768f.m5113a(context);
        }
        try {
            f3695a.add(c1769g);
        } catch (Exception e) {
        }
    }
}
