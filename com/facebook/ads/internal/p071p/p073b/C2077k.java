package com.facebook.ads.internal.p071p.p073b;

import android.util.Log;
import java.lang.Thread.State;
import java.util.concurrent.atomic.AtomicInteger;

class C2077k {
    private final C2088n f4920a;
    private final C2064a f4921b;
    private final Object f4922c = new Object();
    private final Object f4923d = new Object();
    private final AtomicInteger f4924e;
    private volatile Thread f4925f;
    private volatile boolean f4926g;
    private volatile int f4927h = -1;

    private class C2094a implements Runnable {
        final /* synthetic */ C2077k f4962a;

        private C2094a(C2077k c2077k) {
            this.f4962a = c2077k;
        }

        public void run() {
            this.f4962a.m6672e();
        }
    }

    public C2077k(C2088n c2088n, C2064a c2064a) {
        this.f4920a = (C2088n) C2092j.m6733a(c2088n);
        this.f4921b = (C2064a) C2092j.m6733a(c2064a);
        this.f4924e = new AtomicInteger();
    }

    private void m6668b() {
        int i = this.f4924e.get();
        if (i >= 1) {
            this.f4924e.set(0);
            throw new C2090l("Error reading source " + i + " times");
        }
    }

    private void m6669b(long j, long j2) {
        m6679a(j, j2);
        synchronized (this.f4922c) {
            this.f4922c.notifyAll();
        }
    }

    private synchronized void m6670c() {
        Object obj = (this.f4925f == null || this.f4925f.getState() == State.TERMINATED) ? null : 1;
        if (!(this.f4926g || this.f4921b.mo3751d() || obj != null)) {
            this.f4925f = new Thread(new C2094a(), "Source reader for " + this.f4920a);
            this.f4925f.start();
        }
    }

    private void m6671d() {
        synchronized (this.f4922c) {
            try {
                this.f4922c.wait(1000);
            } catch (Throwable e) {
                throw new C2090l("Waiting source data is interrupted!", e);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m6672e() {
        /*
        r8 = this;
        r3 = -1;
        r1 = 0;
        r0 = r8.f4921b;	 Catch:{ Throwable -> 0x006d, all -> 0x006a }
        r1 = r0.mo3746a();	 Catch:{ Throwable -> 0x006d, all -> 0x006a }
        r0 = r8.f4920a;	 Catch:{ Throwable -> 0x006d, all -> 0x006a }
        r0.mo3759a(r1);	 Catch:{ Throwable -> 0x006d, all -> 0x006a }
        r0 = r8.f4920a;	 Catch:{ Throwable -> 0x006d, all -> 0x006a }
        r2 = r0.mo3757a();	 Catch:{ Throwable -> 0x006d, all -> 0x006a }
        r0 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x003f }
    L_0x0017:
        r4 = r8.f4920a;	 Catch:{ Throwable -> 0x003f }
        r4 = r4.mo3758a(r0);	 Catch:{ Throwable -> 0x003f }
        if (r4 == r3) goto L_0x005e;
    L_0x001f:
        r5 = r8.f4923d;	 Catch:{ Throwable -> 0x003f }
        monitor-enter(r5);	 Catch:{ Throwable -> 0x003f }
        r6 = r8.m6674g();	 Catch:{ all -> 0x0051 }
        if (r6 == 0) goto L_0x0032;
    L_0x0028:
        monitor-exit(r5);	 Catch:{ all -> 0x0051 }
        r8.m6675h();
        r0 = (long) r1;
        r2 = (long) r2;
        r8.m6669b(r0, r2);
    L_0x0031:
        return;
    L_0x0032:
        r6 = r8.f4921b;	 Catch:{ all -> 0x0051 }
        r6.mo3748a(r0, r4);	 Catch:{ all -> 0x0051 }
        monitor-exit(r5);	 Catch:{ all -> 0x0051 }
        r1 = r1 + r4;
        r4 = (long) r1;
        r6 = (long) r2;
        r8.m6669b(r4, r6);	 Catch:{ Throwable -> 0x003f }
        goto L_0x0017;
    L_0x003f:
        r0 = move-exception;
    L_0x0040:
        r3 = r8.f4924e;	 Catch:{ all -> 0x0054 }
        r3.incrementAndGet();	 Catch:{ all -> 0x0054 }
        r8.m6680a(r0);	 Catch:{ all -> 0x0054 }
        r8.m6675h();
        r0 = (long) r1;
        r2 = (long) r2;
        r8.m6669b(r0, r2);
        goto L_0x0031;
    L_0x0051:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0051 }
        throw r0;	 Catch:{ Throwable -> 0x003f }
    L_0x0054:
        r0 = move-exception;
    L_0x0055:
        r8.m6675h();
        r4 = (long) r1;
        r2 = (long) r2;
        r8.m6669b(r4, r2);
        throw r0;
    L_0x005e:
        r8.m6673f();	 Catch:{ Throwable -> 0x003f }
        r8.m6675h();
        r0 = (long) r1;
        r2 = (long) r2;
        r8.m6669b(r0, r2);
        goto L_0x0031;
    L_0x006a:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0055;
    L_0x006d:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.p.b.k.e():void");
    }

    private void m6673f() {
        synchronized (this.f4923d) {
            if (!m6674g() && this.f4921b.mo3746a() == this.f4920a.mo3757a()) {
                this.f4921b.mo3750c();
            }
        }
    }

    private boolean m6674g() {
        return Thread.currentThread().isInterrupted() || this.f4926g;
    }

    private void m6675h() {
        try {
            this.f4920a.mo3760b();
        } catch (Throwable e) {
            m6680a(new C2090l("Error closing source " + this.f4920a, e));
        }
    }

    public int m6676a(byte[] bArr, long j, int i) {
        C2095m.m6739a(bArr, j, i);
        while (!this.f4921b.mo3751d() && ((long) this.f4921b.mo3746a()) < ((long) i) + j && !this.f4926g) {
            m6670c();
            m6671d();
            m6668b();
        }
        int a = this.f4921b.mo3747a(bArr, j, i);
        if (this.f4921b.mo3751d() && this.f4927h != 100) {
            this.f4927h = 100;
            mo3755a(100);
        }
        return a;
    }

    public void m6677a() {
        synchronized (this.f4923d) {
            Log.d("ProxyCache", "Shutdown proxy for " + this.f4920a);
            try {
                this.f4926g = true;
                if (this.f4925f != null) {
                    this.f4925f.interrupt();
                }
                this.f4921b.mo3749b();
            } catch (Throwable e) {
                m6680a(e);
            }
        }
    }

    protected void mo3755a(int i) {
    }

    protected void m6679a(long j, long j2) {
        Object obj = 1;
        int i = ((j2 > 0 ? 1 : (j2 == 0 ? 0 : -1)) == 0 ? 1 : null) != null ? 100 : (int) ((100 * j) / j2);
        Object obj2 = i != this.f4927h ? 1 : null;
        if (j2 < 0) {
            obj = null;
        }
        if (!(obj == null || obj2 == null)) {
            mo3755a(i);
        }
        this.f4927h = i;
    }

    protected final void m6680a(Throwable th) {
        if (th instanceof C2091i) {
            Log.d("ProxyCache", "ProxyCache is interrupted");
        } else {
            Log.e("ProxyCache", "ProxyCache error", th);
        }
    }
}
