package com.vungle.publisher;

import android.os.Handler;
import android.os.Looper;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class bz {
    private final Handler f2723a = new Handler(Looper.getMainLooper());

    public void m3482a(Runnable runnable) {
        this.f2723a.post(runnable);
    }

    public void m3483a(Runnable runnable, long j) {
        this.f2723a.postDelayed(runnable, j);
    }

    public void m3481a() {
        this.f2723a.removeCallbacksAndMessages(null);
    }
}
