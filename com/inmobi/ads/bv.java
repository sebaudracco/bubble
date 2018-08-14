package com.inmobi.ads;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

/* compiled from: VisibilityTracker */
abstract class bv {
    static final C3024a f7237a = new C30341();
    private static final String f7238b = bv.class.getSimpleName();
    @NonNull
    private final ArrayList<View> f7239c;
    private long f7240d;
    private boolean f7241e;
    @NonNull
    private final Map<View, C3036d> f7242f;
    @NonNull
    private final C3024a f7243g;
    @Nullable
    private C3025c f7244h;
    @NonNull
    private final C3035b f7245i;
    @NonNull
    private final Handler f7246j;
    private boolean f7247k;

    /* compiled from: VisibilityTracker */
    interface C3024a {
        boolean mo6209a(@Nullable View view, @Nullable View view2, int i, Object obj);
    }

    /* compiled from: VisibilityTracker */
    interface C3025c {
        void mo6210a(List<View> list, List<View> list2);
    }

    /* compiled from: VisibilityTracker */
    static class C30341 implements C3024a {
        private final Rect f7321a = new Rect();

        C30341() {
        }

        public boolean mo6209a(@Nullable View view, @Nullable View view2, int i, Object obj) {
            if (view2 == null || view2.getVisibility() != 0) {
                return false;
            }
            if ((view != null && view.getParent() == null) || !view2.getGlobalVisibleRect(this.f7321a)) {
                return false;
            }
            long height = ((long) this.f7321a.height()) * ((long) this.f7321a.width());
            long height2 = ((long) view2.getHeight()) * ((long) view2.getWidth());
            if (height2 <= 0 || height * 100 < height2 * ((long) i)) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: VisibilityTracker */
    static class C3035b implements Runnable {
        @NonNull
        private final ArrayList<View> f7322a = new ArrayList();
        @NonNull
        private final ArrayList<View> f7323b = new ArrayList();
        private WeakReference<bv> f7324c;

        C3035b(bv bvVar) {
            this.f7324c = new WeakReference(bvVar);
        }

        public void run() {
            if (this.f7324c.get() != null) {
                ((bv) this.f7324c.get()).f7247k = false;
                for (Entry entry : ((bv) this.f7324c.get()).f7242f.entrySet()) {
                    View view = (View) entry.getKey();
                    int i = ((C3036d) entry.getValue()).f7325a;
                    View view2 = ((C3036d) entry.getValue()).f7327c;
                    Object obj = ((C3036d) entry.getValue()).f7328d;
                    if (this.f7324c.get() == null || !((bv) this.f7324c.get()).f7243g.mo6209a(view2, view, i, obj)) {
                        this.f7323b.add(view);
                    } else {
                        this.f7322a.add(view);
                    }
                }
            }
            if (this.f7324c.get() != null) {
                C3025c c = ((bv) this.f7324c.get()).f7244h;
                if (c != null) {
                    c.mo6210a(this.f7322a, this.f7323b);
                }
            }
            this.f7322a.clear();
            this.f7323b.clear();
            if (this.f7324c.get() != null) {
                ((bv) this.f7324c.get()).mo6212b();
            }
        }
    }

    /* compiled from: VisibilityTracker */
    static class C3036d {
        int f7325a;
        long f7326b;
        View f7327c;
        Object f7328d;

        C3036d() {
        }
    }

    protected abstract int mo6211a();

    protected abstract void mo6212b();

    bv() {
        this(f7237a);
    }

    bv(C3024a c3024a) {
        this(new WeakHashMap(10), c3024a, new Handler(Looper.getMainLooper()));
    }

    private bv(@NonNull Map<View, C3036d> map, @NonNull C3024a c3024a, @NonNull Handler handler) {
        this.f7240d = 0;
        this.f7241e = true;
        this.f7242f = map;
        this.f7243g = c3024a;
        this.f7246j = handler;
        this.f7245i = new C3035b(this);
        this.f7239c = new ArrayList(50);
    }

    void m9492a(@Nullable C3025c c3025c) {
        this.f7244h = c3025c;
    }

    public void mo6246c() {
        this.f7245i.run();
        this.f7246j.removeCallbacksAndMessages(null);
        this.f7247k = false;
        this.f7241e = true;
    }

    public void mo6247d() {
        this.f7241e = false;
        m9500i();
    }

    public boolean m9497f() {
        return this.f7241e;
    }

    protected void m9491a(@NonNull View view, @Nullable Object obj, int i) {
        m9490a(view, view, obj, i);
    }

    protected void m9490a(@NonNull View view, @NonNull View view2, @Nullable Object obj, int i) {
        C3036d c3036d = (C3036d) this.f7242f.get(view2);
        if (c3036d == null) {
            c3036d = new C3036d();
            this.f7242f.put(view2, c3036d);
            this.f7240d++;
        }
        c3036d.f7325a = i;
        c3036d.f7326b = this.f7240d;
        c3036d.f7327c = view;
        c3036d.f7328d = obj;
        if (this.f7240d % 50 == 0) {
            m9483a(this.f7240d - 50);
        }
        if (1 == this.f7242f.size()) {
            mo6247d();
        }
    }

    private void m9483a(long j) {
        for (Entry entry : this.f7242f.entrySet()) {
            if (((C3036d) entry.getValue()).f7326b < j) {
                this.f7239c.add(entry.getKey());
            }
        }
        Iterator it = this.f7239c.iterator();
        while (it.hasNext()) {
            m9489a((View) it.next());
        }
        this.f7239c.clear();
    }

    protected void m9489a(@NonNull View view) {
        if (((C3036d) this.f7242f.remove(view)) != null) {
            this.f7240d--;
            if (this.f7242f.size() == 0) {
                mo6246c();
            }
        }
    }

    protected void m9498g() {
        this.f7242f.clear();
        this.f7246j.removeMessages(0);
        this.f7247k = false;
    }

    View m9488a(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        View view;
        for (Entry entry : this.f7242f.entrySet()) {
            if (((C3036d) entry.getValue()).f7328d.equals(obj)) {
                view = (View) entry.getKey();
                break;
            }
        }
        view = null;
        if (view == null) {
            return view;
        }
        m9489a(view);
        return view;
    }

    boolean m9499h() {
        return !this.f7242f.isEmpty();
    }

    protected void mo6248e() {
        m9498g();
        this.f7244h = null;
        this.f7241e = true;
    }

    protected void m9500i() {
        if (!this.f7247k && !this.f7241e) {
            this.f7247k = true;
            this.f7246j.postDelayed(this.f7245i, (long) mo6211a());
        }
    }
}
