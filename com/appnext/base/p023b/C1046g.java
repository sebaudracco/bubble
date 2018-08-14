package com.appnext.base.p023b;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class C1046g {
    private static C1046g jZ;
    private static Context mContext;
    private Handler hW;
    private Handler ka = new Handler(Looper.getMainLooper());
    private HandlerThread kb = new HandlerThread("ExecutesManagerWorkerThread");

    public C1046g() {
        this.kb.start();
        this.hW = new Handler(this.kb.getLooper());
    }

    public static C1046g cw() {
        if (jZ == null) {
            synchronized (C1046g.class) {
                if (jZ == null) {
                    jZ = new C1046g();
                }
            }
        }
        return jZ;
    }

    public void m2139a(Runnable runnable) {
        if (runnable != null) {
            this.ka.post(runnable);
        }
    }

    public void m2140a(Runnable runnable, long j) {
        if (runnable != null) {
            this.ka.postDelayed(runnable, j);
        }
    }

    public void m2141b(Runnable runnable) {
        if (runnable != null) {
            this.hW.post(runnable);
        }
    }

    public void m2142b(Runnable runnable, long j) {
        if (runnable != null) {
            this.hW.postDelayed(runnable, j);
        }
    }

    protected void finalize() {
        try {
            this.hW.removeCallbacks(null);
            this.kb.quit();
            super.finalize();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
