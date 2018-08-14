package com.integralads.avid.library.inmobi;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.integralads.avid.library.inmobi.p124d.C3297e;
import com.integralads.avid.library.inmobi.p124d.C3299b;
import com.integralads.avid.library.inmobi.p125e.C3307a;
import com.integralads.avid.library.inmobi.p126f.C3312a;
import com.integralads.avid.library.inmobi.p126f.C3313b;
import com.integralads.avid.library.inmobi.p126f.C3314c;
import com.integralads.avid.library.inmobi.p126f.C3315d;
import com.integralads.avid.library.inmobi.session.internal.C3333a;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AvidTreeWalker */
public class C3317f {
    private static C3317f f8457a = new C3317f();
    private static C3311b f8458b;
    private static final Runnable f8459j = new C33091();
    private List<C3310a> f8460c = new ArrayList();
    private int f8461d;
    private final C3313b f8462e = new C3313b();
    private final C3314c f8463f = new C3314c();
    private final HashSet<String> f8464g = new HashSet();
    private long f8465h;
    private long f8466i;

    /* compiled from: AvidTreeWalker */
    static class C33091 implements Runnable {
        C33091() {
        }

        public void run() {
            if (C3317f.f8458b != null) {
                C3317f.f8458b.sendEmptyMessage(0);
                C3317f.f8458b.postDelayed(C3317f.f8459j, 100);
            }
        }
    }

    /* compiled from: AvidTreeWalker */
    public interface C3310a {
        void m11277a(int i, long j);
    }

    /* compiled from: AvidTreeWalker */
    private static class C3311b extends Handler {
        private C3311b() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            C3317f.m11303a().m11313i();
        }
    }

    public static C3317f m11303a() {
        return f8457a;
    }

    public void m11317b() {
        m11313i();
        m11311g();
    }

    public void m11318c() {
        m11319d();
        this.f8460c.clear();
        this.f8462e.m11288d();
        this.f8464g.clear();
    }

    public void m11319d() {
        m11312h();
    }

    private void m11311g() {
        if (f8458b == null) {
            f8458b = new C3311b();
            f8458b.postDelayed(f8459j, 100);
        }
    }

    private void m11312h() {
        if (f8458b != null) {
            f8458b.removeCallbacks(f8459j);
            f8458b = null;
        }
    }

    private JSONObject m11304a(View view, C3297e c3297e, JSONObject jSONObject) {
        JSONObject a = c3297e.mo6322a(view);
        boolean a2 = m11308a(view, a);
        this.f8463f.m11290a(view, a, C3307a.m11255a().m11261b());
        if (a2) {
            this.f8463f.m11291b();
        }
        List arrayList;
        if (a2) {
            arrayList = new ArrayList();
        } else {
            arrayList = c3297e.mo6324b(view);
        }
        JSONArray b = this.f8462e.m11285b(jSONObject);
        this.f8462e.m11281a((List) r0, b);
        if (!r0.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            for (View a3 : r0) {
                jSONArray.put(m11304a(a3, c3297e.mo6325c(view), this.f8462e.m11280a(b, i)));
                i++;
            }
            try {
                a.put("childViews", jSONArray);
            } catch (Exception e) {
                C3312a.m11279a("AvidTreeWalker.walkViewTree(): error with populating children", e);
            }
        }
        c3297e.mo6323a(view, a);
        this.f8462e.m11283a(a, jSONObject);
        this.f8461d++;
        return a;
    }

    private boolean m11308a(View view, JSONObject jSONObject) {
        if (view == null) {
            return false;
        }
        C3333a a = C3307a.m11255a().m11256a(view);
        if (a == null || !a.m11404g()) {
            return false;
        }
        this.f8464g.add(a.m11399c());
        C3315d.m11297a(jSONObject, a.m11399c());
        return true;
    }

    private void m11313i() {
        m11314j();
        m11316l();
        m11315k();
    }

    private void m11314j() {
        this.f8461d = 0;
        this.f8465h = C3315d.m11301b();
        this.f8462e.m11287c();
        this.f8463f.m11289a();
    }

    private void m11315k() {
        this.f8466i = C3315d.m11301b();
        m11305a(this.f8466i - this.f8465h);
        this.f8464g.clear();
    }

    private void m11316l() {
        JSONObject a = m11304a(null, C3307a.m11255a().m11265d() ? C3299b.m11221a().m11222b() : C3299b.m11221a().m11224d(), this.f8462e.m11286b());
        if (this.f8462e.m11284a()) {
            this.f8462e.m11282a(a);
            m11307a(a);
        }
    }

    private void m11307a(JSONObject jSONObject) {
        String jSONObject2 = !this.f8464g.isEmpty() ? C3315d.m11295a(jSONObject).toString() : null;
        for (C3333a c3333a : C3307a.m11255a().m11261b()) {
            if (this.f8464g.contains(c3333a.m11399c())) {
                c3333a.m11394a(jSONObject2);
            } else {
                c3333a.m11412o();
            }
        }
    }

    private void m11305a(long j) {
        if (this.f8460c.size() > 0) {
            for (C3310a a : this.f8460c) {
                a.m11277a(this.f8461d, j);
            }
        }
    }
}
