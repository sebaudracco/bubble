package com.moat.analytics.mobile.vng;

import android.os.Handler;
import android.os.Looper;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.altbeacon.beacon.service.scanner.CycledLeScanner;

class C3532w {
    private static C3532w f8993g;
    private static final Queue<C3530c> f8994h = new ConcurrentLinkedQueue();
    volatile C3531d f8995a = C3531d.OFF;
    volatile boolean f8996b = false;
    volatile boolean f8997c = false;
    volatile int f8998d = 200;
    private long f8999e = CycledLeScanner.ANDROID_N_MAX_SCAN_DURATION_MILLIS;
    private long f9000f = 60000;
    private Handler f9001i;
    private final AtomicBoolean f9002j = new AtomicBoolean(false);
    private volatile long f9003k = 0;
    private final AtomicInteger f9004l = new AtomicInteger(0);
    private final AtomicBoolean f9005m = new AtomicBoolean(false);

    interface C3499b {
        void mo6533b();

        void mo6534c();
    }

    interface C3524e {
        void mo6546a(C3501l c3501l);
    }

    class C35272 implements Runnable {
        final /* synthetic */ C3532w f8980a;

        C35272(C3532w c3532w) {
            this.f8980a = c3532w;
        }

        public void run() {
            try {
                if (C3532w.f8994h.size() > 0) {
                    this.f8980a.m12017d();
                    this.f8980a.f9001i.postDelayed(this, 60000);
                    return;
                }
                this.f8980a.f9002j.compareAndSet(true, false);
                this.f8980a.f9001i.removeCallbacks(this);
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }
    }

    private class C3529a implements Runnable {
        final /* synthetic */ C3532w f8983a;
        private final Handler f8984b;
        private final String f8985c;
        private final C3524e f8986d;

        private C3529a(C3532w c3532w, String str, Handler handler, C3524e c3524e) {
            this.f8983a = c3532w;
            this.f8986d = c3524e;
            this.f8984b = handler;
            this.f8985c = "https://z.moatads.com/" + str + "/android/" + "3f2ae9c1894282b5e0222f0d06bbf457191f816f".substring(0, 7) + "/status.json";
        }

        private void m12006a() {
            String b = m12007b();
            final C3501l c3501l = new C3501l(b);
            this.f8983a.f8996b = c3501l.m11938a();
            this.f8983a.f8997c = c3501l.m11939b();
            this.f8983a.f8998d = c3501l.m11940c();
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ C3529a f8982b;

                public void run() {
                    try {
                        this.f8982b.f8986d.mo6546a(c3501l);
                    } catch (Exception e) {
                        C3502m.m11942a(e);
                    }
                }
            });
            this.f8983a.f9003k = System.currentTimeMillis();
            this.f8983a.f9005m.compareAndSet(true, false);
            if (b != null) {
                this.f8983a.f9004l.set(0);
            } else if (this.f8983a.f9004l.incrementAndGet() < 10) {
                this.f8983a.m12010a(this.f8983a.f9000f);
            }
        }

        private String m12007b() {
            try {
                return (String) C3512q.m11980a(this.f8985c + "?ts=" + System.currentTimeMillis() + "&v=" + "2.2.0").m11843b();
            } catch (Exception e) {
                return null;
            }
        }

        public void run() {
            try {
                m12006a();
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
            this.f8984b.removeCallbacks(this);
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                myLooper.quit();
            }
        }
    }

    private class C3530c {
        final Long f8987a;
        final C3499b f8988b;
        final /* synthetic */ C3532w f8989c;

        C3530c(C3532w c3532w, Long l, C3499b c3499b) {
            this.f8989c = c3532w;
            this.f8987a = l;
            this.f8988b = c3499b;
        }
    }

    enum C3531d {
        OFF,
        ON
    }

    private C3532w() {
        try {
            this.f9001i = new Handler(Looper.getMainLooper());
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }

    static synchronized C3532w m12009a() {
        C3532w c3532w;
        synchronized (C3532w.class) {
            if (f8993g == null) {
                f8993g = new C3532w();
            }
            c3532w = f8993g;
        }
        return c3532w;
    }

    private void m12010a(final long j) {
        if (this.f9005m.compareAndSet(false, true)) {
            C3511p.m11976a(3, "OnOff", (Object) this, "Performing status check.");
            new Thread(this) {
                final /* synthetic */ C3532w f8979b;

                class C35251 implements C3524e {
                    final /* synthetic */ C35261 f8977a;

                    C35251(C35261 c35261) {
                        this.f8977a = c35261;
                    }

                    public void mo6546a(C3501l c3501l) {
                        synchronized (C3532w.f8994h) {
                            boolean z = ((C3500k) MoatAnalytics.getInstance()).f8940a;
                            if (this.f8977a.f8979b.f8995a != c3501l.m11941d() || (this.f8977a.f8979b.f8995a == C3531d.OFF && z)) {
                                this.f8977a.f8979b.f8995a = c3501l.m11941d();
                                if (this.f8977a.f8979b.f8995a == C3531d.OFF && z) {
                                    this.f8977a.f8979b.f8995a = C3531d.ON;
                                }
                                if (this.f8977a.f8979b.f8995a == C3531d.ON) {
                                    C3511p.m11976a(3, "OnOff", (Object) this, "Moat enabled - Version 2.2.0");
                                }
                                for (C3530c c3530c : C3532w.f8994h) {
                                    if (this.f8977a.f8979b.f8995a == C3531d.ON) {
                                        c3530c.f8988b.mo6533b();
                                    } else {
                                        c3530c.f8988b.mo6534c();
                                    }
                                }
                            }
                            while (!C3532w.f8994h.isEmpty()) {
                                C3532w.f8994h.remove();
                            }
                        }
                    }
                }

                public void run() {
                    Looper.prepare();
                    Handler handler = new Handler();
                    String str = "VNG";
                    handler.postDelayed(new C3529a("VNG", handler, new C35251(this)), j);
                    Looper.loop();
                }
            }.start();
        }
    }

    private void m12017d() {
        synchronized (f8994h) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = f8994h.iterator();
            while (it.hasNext()) {
                if (currentTimeMillis - ((C3530c) it.next()).f8987a.longValue() >= 60000) {
                    it.remove();
                }
            }
            if (f8994h.size() >= 15) {
                for (int i = 0; i < 5; i++) {
                    f8994h.remove();
                }
            }
        }
    }

    private void m12019e() {
        if (this.f9002j.compareAndSet(false, true)) {
            this.f9001i.postDelayed(new C35272(this), 60000);
        }
    }

    void m12021a(C3499b c3499b) {
        if (this.f8995a == C3531d.ON) {
            c3499b.mo6533b();
            return;
        }
        m12017d();
        f8994h.add(new C3530c(this, Long.valueOf(System.currentTimeMillis()), c3499b));
        m12019e();
    }

    void m12022b() {
        if (System.currentTimeMillis() - this.f9003k > this.f8999e) {
            m12010a(0);
        }
    }
}
