package com.unit.two.p151f;

import android.os.Handler;
import android.os.Looper;
import com.unit.two.p147c.C4096a;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class C4150q {
    private static Handler f9720a = new Handler(Looper.getMainLooper());
    private static ExecutorService f9721b = Executors.newCachedThreadPool();

    public static void m12870a() {
        if ((Looper.myLooper() == Looper.getMainLooper() ? 1 : null) != null) {
            throw new IllegalStateException(C4096a.aR);
        }
    }

    public static void m12871a(Runnable runnable) {
        if (0 <= 0) {
            f9721b.execute(runnable);
        } else {
            f9720a.postDelayed(new C4151r(runnable), 0);
        }
    }
}
