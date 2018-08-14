package com.facebook.ads.internal.p033n;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.aa;
import com.facebook.ads.internal.adapters.ab;
import com.facebook.ads.internal.adapters.ac;
import com.facebook.ads.internal.d.b;
import com.facebook.ads.internal.n.e.1;
import com.facebook.ads.internal.n.e.2;
import com.facebook.ads.internal.n.e.3;
import com.facebook.ads.internal.n.e.4;
import com.facebook.ads.internal.n.e.5;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.g;
import com.facebook.ads.internal.n.h;
import com.facebook.ads.internal.n.i;
import com.facebook.ads.internal.n.j;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C1562f;
import com.facebook.ads.internal.protocol.d;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.view.b.c;
import com.facebook.ads.internal.view.x;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;

public class C1560e {
    private static final d f2575b = d.a;
    private static final String f2576c = C1560e.class.getSimpleName();
    private static WeakHashMap<View, WeakReference<C1560e>> f2577d = new WeakHashMap();
    @Deprecated
    private boolean f2578A;
    private long f2579B;
    @Nullable
    private c f2580C;
    private View f2581D;
    private String f2582E;
    private boolean f2583F;
    @Nullable
    protected ab f2584a;
    private final Context f2585e;
    private final String f2586f;
    private final String f2587g;
    private final b f2588h;
    private com.facebook.ads.internal.n.b f2589i;
    private final C1560e.d f2590j;
    private DisplayAdController f2591k;
    private volatile boolean f2592l;
    private com.facebook.ads.internal.h.d f2593m;
    @Nullable
    private View f2594n;
    private final List<View> f2595o;
    private OnTouchListener f2596p;
    private a f2597q;
    private a.a f2598r;
    private final s f2599s;
    @Nullable
    private aa f2600t;
    private C1560e.a f2601u;
    private C1560e.b f2602v;
    private x f2603w;
    private i f2604x;
    private boolean f2605y;
    private boolean f2606z;

    public C1560e(Context context, ab abVar, com.facebook.ads.internal.h.d dVar, C1560e.d dVar2) {
        this(context, null, dVar2);
        this.f2584a = abVar;
        this.f2593m = dVar;
        this.f2592l = true;
        this.f2581D = new View(context);
    }

    public C1560e(Context context, String str, C1560e.d dVar) {
        this.f2587g = UUID.randomUUID().toString();
        this.f2595o = new ArrayList();
        this.f2599s = new s();
        this.f2606z = false;
        this.f2583F = false;
        this.f2585e = context;
        this.f2586f = str;
        this.f2590j = dVar;
        this.f2588h = new b(context);
        this.f2581D = new View(context);
    }

    public C1560e(C1560e c1560e) {
        this(c1560e.f2585e, null, c1560e.f2590j);
        this.f2593m = c1560e.f2593m;
        this.f2584a = c1560e.f2584a;
        this.f2592l = true;
        this.f2581D = new View(this.f2585e);
    }

    private int m3346G() {
        return this.f2593m != null ? this.f2593m.g() : (this.f2591k == null || this.f2591k.a() == null) ? 0 : this.f2591k.a().g();
    }

    private int m3347H() {
        return this.f2593m != null ? this.f2593m.h() : this.f2584a != null ? this.f2584a.j() : (this.f2591k == null || this.f2591k.a() == null) ? 0 : this.f2591k.a().h();
    }

    private int m3348I() {
        return this.f2593m != null ? this.f2593m.i() : this.f2584a != null ? this.f2584a.k() : (this.f2591k == null || this.f2591k.a() == null) ? 1000 : this.f2591k.a().i();
    }

    private boolean m3349J() {
        return m3417z() == j.a ? this.f2578A : m3417z() == j.b;
    }

    private void m3350K() {
        for (View view : this.f2595o) {
            view.setOnClickListener(null);
            view.setOnTouchListener(null);
            view.setOnLongClickListener(null);
        }
        this.f2595o.clear();
    }

    private void m3351L() {
        if (this.f2584a != null && this.f2584a.d()) {
            this.f2602v = new C1560e.b(this, null);
            this.f2602v.a();
            this.f2600t = new aa(this.f2585e, new 4(this), this.f2597q, this.f2584a);
        }
    }

    public static void m3354a(f fVar, ImageView imageView) {
        if (fVar != null && imageView != null) {
            new com.facebook.ads.internal.view.b.d(imageView).a(fVar.c(), fVar.b()).a(fVar.a());
        }
    }

    private void m3355a(List<View> list, View view) {
        if (this.f2590j == null || !this.f2590j.a(view)) {
            list.add(view);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    m3355a((List) list, viewGroup.getChildAt(i));
                }
            }
        }
    }

    private void m3357b(View view) {
        this.f2595o.add(view);
        view.setOnClickListener(this.f2601u);
        view.setOnTouchListener(this.f2601u);
        if (com.facebook.ads.internal.l.a.b(view.getContext())) {
            view.setOnLongClickListener(this.f2601u);
        }
    }

    public List<C1560e> m3374A() {
        return !m3397f() ? null : this.f2584a.B();
    }

    @Nullable
    public String m3375B() {
        return !m3397f() ? null : this.f2584a.c();
    }

    public void m3376C() {
        this.f2581D.performClick();
    }

    public void m3377D() {
        if (this.f2594n != null) {
            if (f2577d.containsKey(this.f2594n) && ((WeakReference) f2577d.get(this.f2594n)).get() == this) {
                if ((this.f2594n instanceof ViewGroup) && this.f2603w != null) {
                    ((ViewGroup) this.f2594n).removeView(this.f2603w);
                    this.f2603w = null;
                }
                if (this.f2584a != null) {
                    this.f2584a.b_();
                }
                if (this.f2580C != null && com.facebook.ads.internal.l.a.b(this.f2585e)) {
                    this.f2580C.b();
                    this.f2594n.getOverlay().remove(this.f2580C);
                }
                f2577d.remove(this.f2594n);
                m3350K();
                this.f2594n = null;
                if (this.f2597q != null) {
                    this.f2597q.b();
                    this.f2597q = null;
                }
                this.f2600t = null;
                return;
            }
            throw new IllegalStateException("View not registered with this NativeAd");
        }
    }

    public void m3378E() {
        if (this.f2583F) {
            this.f2600t = new aa(this.f2585e, new 5(this), this.f2597q, this.f2584a);
        }
    }

    public void m3379F() {
        if (this.f2600t != null) {
            this.f2600t.a();
        }
    }

    public ab m3380a() {
        return this.f2584a;
    }

    public void m3381a(OnTouchListener onTouchListener) {
        this.f2596p = onTouchListener;
    }

    public void m3382a(View view) {
        List arrayList = new ArrayList();
        m3355a(arrayList, view);
        m3383a(view, arrayList);
    }

    public void m3383a(View view, List<View> list) {
        if (view == null) {
            throw new IllegalArgumentException("Must provide a View");
        } else if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("Invalid set of clickable views");
        } else if (m3397f()) {
            if (this.f2594n != null) {
                Log.w(f2576c, "Native Ad was already registered with a View. Auto unregistering and proceeding.");
                m3377D();
            }
            if (f2577d.containsKey(view)) {
                Log.w(f2576c, "View already registered with a NativeAd. Auto unregistering and proceeding.");
                ((C1560e) ((WeakReference) f2577d.get(view)).get()).m3377D();
            }
            this.f2601u = new C1560e.a(this, null);
            this.f2594n = view;
            if (view instanceof ViewGroup) {
                this.f2603w = new x(view.getContext(), new 2(this));
                ((ViewGroup) view).addView(this.f2603w);
            }
            List<View> arrayList = new ArrayList(list);
            if (this.f2581D != null) {
                arrayList.add(this.f2581D);
            }
            for (View b : arrayList) {
                m3357b(b);
            }
            this.f2584a.a(view, arrayList);
            int d = m3395d();
            this.f2598r = new 3(this);
            this.f2597q = new a(this.f2594n, d, m3346G(), true, this.f2598r);
            this.f2597q.a(m3347H());
            this.f2597q.b(m3348I());
            this.f2597q.a();
            this.f2600t = new aa(this.f2585e, new C1560e.c(this, null), this.f2597q, this.f2584a);
            this.f2600t.a(arrayList);
            f2577d.put(view, new WeakReference(this));
            if (com.facebook.ads.internal.l.a.b(this.f2585e)) {
                this.f2580C = new c();
                this.f2580C.a(this.f2586f);
                this.f2580C.b(this.f2585e.getPackageName());
                this.f2580C.a(this.f2597q);
                if (this.f2584a.D() > 0) {
                    this.f2580C.a(this.f2584a.D(), this.f2584a.C());
                }
                if (this.f2593m != null) {
                    this.f2580C.a(this.f2593m.a());
                } else if (!(this.f2591k == null || this.f2591k.a() == null)) {
                    this.f2580C.a(this.f2591k.a().a());
                }
                this.f2594n.getOverlay().add(this.f2580C);
            }
        } else {
            Log.e(f2576c, "Ad not loaded");
        }
    }

    public void m3384a(ac acVar) {
        if (this.f2584a != null) {
            this.f2584a.a(acVar);
        }
    }

    public void m3385a(com.facebook.ads.internal.n.b bVar) {
        this.f2589i = bVar;
    }

    public void m3386a(i iVar) {
        this.f2604x = iVar;
    }

    public void m3387a(String str) {
        this.f2583F = true;
        this.f2582E = str;
    }

    public void m3388a(Set<com.facebook.ads.internal.n.d> set, String str) {
        if (this.f2592l) {
            throw new IllegalStateException("loadAd cannot be called more than once");
        }
        this.f2579B = System.currentTimeMillis();
        this.f2592l = true;
        this.f2591k = new DisplayAdController(this.f2585e, this.f2586f, C1562f.NATIVE_UNKNOWN, AdPlacementType.NATIVE, null, f2575b, 1, true);
        this.f2591k.a(new 1(this, set));
        this.f2591k.a(str);
    }

    public void m3389a(boolean z) {
        this.f2578A = z;
    }

    @Nullable
    public com.facebook.ads.internal.n.c m3390b() {
        return (!m3397f() || this.f2584a == null) ? null : this.f2584a.E();
    }

    public void m3391b(String str) {
        if (this.f2584a != null) {
            Map hashMap = new HashMap();
            hashMap.put("eil", String.valueOf(true));
            hashMap.put("eil_source", str);
            this.f2584a.b(hashMap);
        }
    }

    public void m3392b(boolean z) {
        this.f2605y = z;
    }

    public void m3393c() {
        if (this.f2602v != null) {
            this.f2602v.b();
            this.f2602v = null;
        }
        if (this.f2591k != null) {
            this.f2591k.b(true);
            this.f2591k = null;
        }
    }

    public void m3394c(boolean z) {
        this.f2606z = z;
    }

    public int m3395d() {
        return this.f2593m != null ? this.f2593m.f() : (this.f2591k == null || this.f2591k.a() == null) ? 1 : this.f2591k.a().f();
    }

    public String m3396e() {
        return this.f2586f;
    }

    public boolean m3397f() {
        return this.f2584a != null && this.f2584a.c_();
    }

    public boolean m3398g() {
        return m3397f() && this.f2584a.g();
    }

    public boolean m3399h() {
        return this.f2584a != null && this.f2584a.a_();
    }

    public f m3400i() {
        return !m3397f() ? null : this.f2584a.l();
    }

    public f m3401j() {
        return !m3397f() ? null : this.f2584a.m();
    }

    public h m3402k() {
        return !m3397f() ? null : this.f2584a.n();
    }

    public String m3403l() {
        return !m3397f() ? null : this.f2584a.o();
    }

    public String m3404m() {
        return !m3397f() ? null : this.f2584a.p();
    }

    public String m3405n() {
        return !m3397f() ? null : this.f2584a.q();
    }

    public String m3406o() {
        return !m3397f() ? null : this.f2584a.G();
    }

    public String m3407p() {
        return !m3397f() ? null : this.f2584a.r();
    }

    public String m3408q() {
        return !m3397f() ? null : this.f2584a.s();
    }

    public g m3409r() {
        return !m3397f() ? null : this.f2584a.t();
    }

    public String m3410s() {
        return !m3397f() ? null : this.f2587g;
    }

    public f m3411t() {
        return !m3397f() ? null : this.f2584a.u();
    }

    public String m3412u() {
        return !m3397f() ? null : this.f2584a.v();
    }

    public String m3413v() {
        return !m3397f() ? null : this.f2584a.w();
    }

    public String m3414w() {
        return (!m3397f() || TextUtils.isEmpty(this.f2584a.x())) ? null : this.f2588h.b(this.f2584a.x());
    }

    public String m3415x() {
        return !m3397f() ? null : this.f2584a.y();
    }

    public String m3416y() {
        return !m3397f() ? null : this.f2584a.A();
    }

    public j m3417z() {
        return !m3397f() ? j.a : this.f2584a.z();
    }
}
