package com.inmobi.rendering.p118a;

import android.os.SystemClock;
import android.support.annotation.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@VisibleForTesting
/* compiled from: Click */
public final class C3194a {
    int f7997a;
    @VisibleForTesting
    public String f7998b;
    @VisibleForTesting
    public Map<String, String> f7999c;
    long f8000d;
    long f8001e;
    int f8002f;
    AtomicBoolean f8003g;
    boolean f8004h;
    boolean f8005i;

    public C3194a(String str, boolean z, boolean z2, int i) {
        this(new Random().nextInt() & Integer.MAX_VALUE, str, new HashMap(), z, z2, i, System.currentTimeMillis(), SystemClock.elapsedRealtime());
    }

    public C3194a(String str, Map<String, String> map, boolean z, boolean z2, int i) {
        this(new Random().nextInt() & Integer.MAX_VALUE, str, map, z, z2, i, System.currentTimeMillis(), SystemClock.elapsedRealtime());
    }

    public C3194a(int i, String str, Map<String, String> map, boolean z, boolean z2, int i2, long j, long j2) {
        this.f7997a = i;
        this.f7998b = str;
        this.f7999c = map;
        this.f8000d = j;
        this.f8001e = j2;
        this.f8002f = i2;
        this.f8003g = new AtomicBoolean(false);
        this.f8005i = z;
        this.f8004h = z2;
    }

    public boolean m10668a(long j) {
        return SystemClock.elapsedRealtime() - this.f8001e > 1000 * j;
    }
}
