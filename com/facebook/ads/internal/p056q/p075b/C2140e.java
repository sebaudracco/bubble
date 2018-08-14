package com.facebook.ads.internal.p056q.p075b;

import android.graphics.Bitmap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class C2140e {
    static final int f5110a = Runtime.getRuntime().availableProcessors();
    static final ExecutorService f5111b = Executors.newFixedThreadPool(f5110a);
    private static volatile boolean f5112c = true;
    private final Bitmap f5113d;
    private Bitmap f5114e;
    private final C2135a f5115f = new C2139d();

    public C2140e(Bitmap bitmap) {
        this.f5113d = bitmap;
    }

    public Bitmap m6851a() {
        return this.f5114e;
    }

    public Bitmap m6852a(int i) {
        this.f5114e = this.f5115f.mo3761a(this.f5113d, (float) i);
        return this.f5114e;
    }
}
