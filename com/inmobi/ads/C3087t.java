package com.inmobi.ads;

import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.inmobi.ads.C3046c.C3044h;
import com.inmobi.ads.bv.C3025c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

/* compiled from: ImpressionTracker */
class C3087t {
    private static final String f7545a = C3087t.class.getSimpleName();
    @NonNull
    private final bv f7546b;
    @NonNull
    private final Map<View, C3085b> f7547c;
    @NonNull
    private final Map<View, C3085b> f7548d;
    @NonNull
    private final Handler f7549e;
    @NonNull
    private final C3086c f7550f;
    private final long f7551g;
    @Nullable
    private C3025c f7552h;
    @NonNull
    private C2969a f7553i;

    /* compiled from: ImpressionTracker */
    interface C2969a {
        void mo6147a(View view, Object obj);
    }

    /* compiled from: ImpressionTracker */
    class C30841 implements C3025c {
        final /* synthetic */ C3087t f7538a;

        C30841(C3087t c3087t) {
            this.f7538a = c3087t;
        }

        public void mo6210a(@NonNull List<View> list, @NonNull List<View> list2) {
            for (View view : list) {
                C3085b c3085b = (C3085b) this.f7538a.f7547c.get(view);
                if (c3085b == null) {
                    this.f7538a.m9986a(view);
                } else {
                    C3085b c3085b2 = (C3085b) this.f7538a.f7548d.get(view);
                    if (c3085b2 == null || !c3085b.f7539a.equals(c3085b2.f7539a)) {
                        c3085b.m9978a();
                        this.f7538a.f7548d.put(view, c3085b);
                    }
                }
            }
            for (View view2 : list2) {
                this.f7538a.f7548d.remove(view2);
            }
            this.f7538a.m9992f();
        }
    }

    /* compiled from: ImpressionTracker */
    private static class C3085b {
        Object f7539a;
        int f7540b;
        int f7541c;
        long f7542d = Long.MAX_VALUE;

        C3085b(Object obj, int i, int i2) {
            this.f7539a = obj;
            this.f7540b = i;
            this.f7541c = i2;
        }

        void m9978a() {
            this.f7542d = SystemClock.uptimeMillis();
        }
    }

    /* compiled from: ImpressionTracker */
    static class C3086c implements Runnable {
        @NonNull
        private final ArrayList<View> f7543a = new ArrayList();
        private WeakReference<C3087t> f7544b;

        C3086c(C3087t c3087t) {
            this.f7544b = new WeakReference(c3087t);
        }

        public void run() {
            C3087t c3087t = (C3087t) this.f7544b.get();
            if (c3087t != null) {
                for (Entry entry : c3087t.f7548d.entrySet()) {
                    View view = (View) entry.getKey();
                    C3085b c3085b = (C3085b) entry.getValue();
                    if (C3087t.m9982b(c3085b.f7542d, c3085b.f7541c) && this.f7544b.get() != null) {
                        c3087t.f7553i.mo6147a(view, c3085b.f7539a);
                        this.f7543a.add(view);
                    }
                }
                Iterator it = this.f7543a.iterator();
                while (it.hasNext()) {
                    c3087t.m9986a((View) it.next());
                }
                this.f7543a.clear();
                if (!c3087t.f7548d.isEmpty()) {
                    c3087t.m9992f();
                }
            }
        }
    }

    C3087t(C3044h c3044h, @NonNull bv bvVar, @NonNull C2969a c2969a) {
        this(new WeakHashMap(), new WeakHashMap(), bvVar, new Handler(), c3044h, c2969a);
    }

    C3087t(@NonNull Map<View, C3085b> map, @NonNull Map<View, C3085b> map2, @NonNull bv bvVar, @NonNull Handler handler, @NonNull C3044h c3044h, @NonNull C2969a c2969a) {
        this.f7547c = map;
        this.f7548d = map2;
        this.f7546b = bvVar;
        this.f7551g = (long) c3044h.m9701f();
        this.f7552h = new C30841(this);
        this.f7546b.m9492a(this.f7552h);
        this.f7549e = handler;
        this.f7550f = new C3086c(this);
        this.f7553i = c2969a;
    }

    void m9987a(View view, @NonNull Object obj, int i, int i2) {
        C3085b c3085b = (C3085b) this.f7547c.get(view);
        if (c3085b == null || !c3085b.f7539a.equals(obj)) {
            m9986a(view);
            c3085b = new C3085b(obj, i, i2);
            this.f7547c.put(view, c3085b);
            this.f7546b.m9491a(view, obj, c3085b.f7540b);
        }
    }

    void m9986a(View view) {
        this.f7547c.remove(view);
        this.f7548d.remove(view);
        this.f7546b.m9489a(view);
    }

    View m9984a(@NonNull Object obj) {
        View view;
        for (Entry entry : this.f7547c.entrySet()) {
            if (((C3085b) entry.getValue()).f7539a.equals(obj)) {
                view = (View) entry.getKey();
                break;
            }
        }
        view = null;
        if (view != null) {
            m9986a(view);
        }
        return view;
    }

    void m9985a() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7545a, "Impression Tracker paused");
        this.f7546b.m9498g();
        this.f7549e.removeCallbacksAndMessages(null);
        this.f7548d.clear();
    }

    void m9988b() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7545a, "Impression Tracker resumed");
        for (Entry entry : this.f7547c.entrySet()) {
            this.f7546b.m9491a((View) entry.getKey(), ((C3085b) entry.getValue()).f7539a, ((C3085b) entry.getValue()).f7540b);
        }
        m9992f();
        this.f7546b.mo6247d();
    }

    void m9989c() {
        this.f7547c.clear();
        this.f7548d.clear();
        this.f7546b.m9498g();
        this.f7549e.removeMessages(0);
    }

    boolean m9990d() {
        return !this.f7547c.isEmpty();
    }

    void m9991e() {
        m9989c();
        this.f7546b.mo6248e();
        this.f7552h = null;
    }

    void m9992f() {
        if (!this.f7549e.hasMessages(0)) {
            this.f7549e.postDelayed(this.f7550f, this.f7551g);
        }
    }

    private static boolean m9982b(long j, int i) {
        return SystemClock.uptimeMillis() - j >= ((long) i);
    }
}
