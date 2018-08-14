package com.moat.analytics.mobile.mpub;

import android.os.Handler;
import android.os.Looper;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.altbeacon.beacon.service.scanner.CycledLeScanner;

final class C3460t {
    private static C3460t f8817;
    private static final Queue<C3459e> f8818 = new ConcurrentLinkedQueue();
    private long f8819 = 60000;
    volatile int f8820 = 200;
    private long f8821 = CycledLeScanner.ANDROID_N_MAX_SCAN_DURATION_MILLIS;
    volatile boolean f8822 = false;
    private final AtomicBoolean f8823 = new AtomicBoolean(false);
    volatile int f8824 = C3456a.f8807;
    volatile boolean f8825 = false;
    private final AtomicBoolean f8826 = new AtomicBoolean(false);
    volatile int f8827 = 10;
    private final AtomicInteger f8828 = new AtomicInteger(0);
    private volatile long f8829 = 0;
    private Handler f8830;

    interface C3418b {
        void mo6514() throws C3442o;
    }

    interface C3452c {
        void mo6525(C3420g c3420g) throws C3442o;
    }

    class C34554 implements Runnable {
        private /* synthetic */ C3460t f8805;

        C34554(C3460t c3460t) {
            this.f8805 = c3460t;
        }

        public final void run() {
            try {
                if (C3460t.f8818.size() > 0) {
                    C3460t.m11797();
                    this.f8805.f8830.postDelayed(this, 60000);
                    return;
                }
                this.f8805.f8823.compareAndSet(true, false);
                this.f8805.f8830.removeCallbacks(this);
            } catch (Exception e) {
                C3442o.m11756(e);
            }
        }
    }

    enum C3456a {
        ;
        
        public static final int f8806 = 0;
        public static final int f8807 = 0;

        static {
            f8807 = 1;
            f8806 = 2;
            int[] iArr = new int[]{1, 2};
        }
    }

    class C3458d implements Runnable {
        private /* synthetic */ C3460t f8810;
        private final String f8811;
        private final C34532 f8812;
        private final Handler f8813;

        private C3458d(C3460t c3460t, String str, Handler handler, C34532 c34532) {
            this.f8810 = c3460t;
            this.f8812 = c34532;
            this.f8813 = handler;
            this.f8811 = "https://z.moatads.com/" + str + "/android/" + BuildConfig.REVISION.substring(0, 7) + "/status.json";
        }

        private String m11794() {
            try {
                return (String) C3438m.m11734(this.f8811 + "?ts=" + System.currentTimeMillis() + "&v=2.4.1").get();
            } catch (Exception e) {
                return null;
            }
        }

        public final void run() {
            try {
                String ˎ = m11794();
                final C3420g c3420g = new C3420g(ˎ);
                this.f8810.f8825 = c3420g.m11679();
                this.f8810.f8822 = c3420g.m11683();
                this.f8810.f8820 = c3420g.m11682();
                this.f8810.f8827 = c3420g.m11681();
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    private /* synthetic */ C3458d f8808;

                    public final void run() {
                        try {
                            this.f8808.f8812.mo6525(c3420g);
                        } catch (Exception e) {
                            C3442o.m11756(e);
                        }
                    }
                });
                this.f8810.f8829 = System.currentTimeMillis();
                this.f8810.f8826.compareAndSet(true, false);
                if (ˎ != null) {
                    this.f8810.f8828.set(0);
                } else if (this.f8810.f8828.incrementAndGet() < 10) {
                    this.f8810.m11804(this.f8810.f8819);
                }
            } catch (Exception e) {
                C3442o.m11756(e);
            }
            this.f8813.removeCallbacks(this);
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                myLooper.quit();
            }
        }
    }

    class C3459e {
        final Long f8814;
        private /* synthetic */ C3460t f8815;
        final C3418b f8816;

        C3459e(C3460t c3460t, Long l, C3418b c3418b) {
            this.f8815 = c3460t;
            this.f8814 = l;
            this.f8816 = c3418b;
        }
    }

    static synchronized C3460t m11803() {
        C3460t c3460t;
        synchronized (C3460t.class) {
            if (f8817 == null) {
                f8817 = new C3460t();
            }
            c3460t = f8817;
        }
        return c3460t;
    }

    private C3460t() {
        try {
            this.f8830 = new Handler(Looper.getMainLooper());
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }

    final void m11809() {
        if (System.currentTimeMillis() - this.f8829 > this.f8821) {
            m11804(0);
        }
    }

    private void m11804(final long j) {
        if (this.f8826.compareAndSet(false, true)) {
            C3412a.m11642(3, "OnOff", this, "Performing status check.");
            new Thread(this) {
                final /* synthetic */ C3460t f8803;

                class C34532 implements C3452c {
                    private /* synthetic */ C34542 f8802;

                    C34532(C34542 c34542) {
                        this.f8802 = c34542;
                    }

                    public final void mo6525(C3420g c3420g) throws C3442o {
                        synchronized (C3460t.f8818) {
                            boolean z = ((C3419f) MoatAnalytics.getInstance()).f8686;
                            if (this.f8802.f8803.f8824 != c3420g.m11680() || (this.f8802.f8803.f8824 == C3456a.f8807 && z)) {
                                this.f8802.f8803.f8824 = c3420g.m11680();
                                if (this.f8802.f8803.f8824 == C3456a.f8807 && z) {
                                    this.f8802.f8803.f8824 = C3456a.f8806;
                                }
                                if (this.f8802.f8803.f8824 == C3456a.f8806) {
                                    C3412a.m11642(3, "OnOff", this, "Moat enabled - Version 2.4.1");
                                }
                                for (C3459e c3459e : C3460t.f8818) {
                                    if (this.f8802.f8803.f8824 == C3456a.f8806) {
                                        c3459e.f8816.mo6514();
                                    }
                                }
                            }
                            while (!C3460t.f8818.isEmpty()) {
                                C3460t.f8818.remove();
                            }
                        }
                    }
                }

                public final void run() {
                    Looper.prepare();
                    Handler handler = new Handler();
                    handler.postDelayed(new C3458d(BuildConfig.NAMESPACE, handler, new C34532(this)), j);
                    Looper.loop();
                }
            }.start();
        }
    }

    final void m11808(C3418b c3418b) throws C3442o {
        if (this.f8824 == C3456a.f8806) {
            c3418b.mo6514();
            return;
        }
        C3460t.m11797();
        f8818.add(new C3459e(this, Long.valueOf(System.currentTimeMillis()), c3418b));
        if (this.f8823.compareAndSet(false, true)) {
            this.f8830.postDelayed(new C34554(this), 60000);
        }
    }

    private static void m11797() {
        synchronized (f8818) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = f8818.iterator();
            while (it.hasNext()) {
                if (currentTimeMillis - ((C3459e) it.next()).f8814.longValue() >= 60000) {
                    it.remove();
                }
            }
            if (f8818.size() >= 15) {
                for (int i = 0; i < 5; i++) {
                    f8818.remove();
                }
            }
        }
    }
}
