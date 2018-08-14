package com.facebook.ads.internal.view.p053e;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.appnext.base.p023b.C1042c;
import com.facebook.ads.internal.p056q.p057a.C1912o;
import com.facebook.ads.internal.p056q.p057a.C2132u;
import com.facebook.ads.internal.p060b.C1909b;
import com.facebook.ads.internal.p060b.C1942a;
import com.facebook.ads.internal.p060b.C1944c;
import com.facebook.ads.internal.p060b.C1944c.C1943a;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.p053e.p083a.C2222a;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C2322d implements C1912o<Bundle> {
    private final String f5618a;
    private boolean f5619b;
    private final Context f5620c;
    private final C2012c f5621d;
    private final C2247a f5622e;
    private final C1942a f5623f;
    private int f5624g;
    private int f5625h;
    private final C2225a f5626i;

    public interface C2247a {
        boolean mo3781g();

        int getCurrentPosition();

        boolean getGlobalVisibleRect(Rect rect);

        long getInitialBufferTime();

        int getMeasuredHeight();

        int getMeasuredWidth();

        C2222a getVideoStartReason();

        float getVolume();

        boolean mo3786i();
    }

    protected enum C2326b {
        PLAY(0),
        SKIP(1),
        TIME(2),
        MRC(3),
        PAUSE(4),
        RESUME(5),
        MUTE(6),
        UNMUTE(7),
        VIEWABLE_IMPRESSION(10);
        
        public final int f5657j;

        private C2326b(int i) {
            this.f5657j = i;
        }
    }

    public C2322d(Context context, C2012c c2012c, C2247a c2247a, List<C1909b> list, String str) {
        this(context, c2012c, c2247a, list, str, null);
    }

    public C2322d(Context context, C2012c c2012c, C2247a c2247a, List<C1909b> list, String str, @Nullable Bundle bundle) {
        this.f5619b = true;
        this.f5624g = 0;
        this.f5625h = 0;
        this.f5620c = context;
        this.f5621d = c2012c;
        this.f5622e = c2247a;
        this.f5618a = str;
        final C2012c c2012c2 = c2012c;
        final String str2 = str;
        list.add(new C1909b(this, 0.5d, -1.0d, 2.0d, true) {
            final /* synthetic */ C2322d f5643g;

            protected void mo3675a(boolean z, boolean z2, C1944c c1944c) {
                c2012c2.mo3713d(str2, this.f5643g.m7315a(C2326b.MRC));
            }
        });
        c2012c2 = c2012c;
        str2 = str;
        list.add(new C1909b(this, 1.0E-7d, -1.0d, 0.001d, false) {
            final /* synthetic */ C2322d f5646g;

            protected void mo3675a(boolean z, boolean z2, C1944c c1944c) {
                c2012c2.mo3713d(str2, this.f5646g.m7315a(C2326b.VIEWABLE_IMPRESSION));
            }
        });
        if (bundle != null) {
            this.f5623f = new C1942a((View) c2247a, list, bundle.getBundle("adQualityManager"));
            this.f5624g = bundle.getInt("lastProgressTimeMS");
            this.f5625h = bundle.getInt("lastBoundaryTimeMS");
        } else {
            this.f5623f = new C1942a((View) c2247a, list);
        }
        this.f5626i = new C2225a(new Handler(), this);
    }

    private Map<String, String> m7315a(C2326b c2326b) {
        return m7316a(c2326b, this.f5622e.getCurrentPosition());
    }

    private Map<String, String> m7316a(C2326b c2326b, int i) {
        Map<String, String> c = m7322c(i);
        c.put(C1042c.jL, String.valueOf(c2326b.f5657j));
        return c;
    }

    private void m7318a(int i, boolean z) {
        if (((double) i) > 0.0d && i >= this.f5624g) {
            if (i > this.f5624g) {
                this.f5623f.m6126a((double) (((float) (i - this.f5624g)) / 1000.0f), (double) m7329d());
                this.f5624g = i;
                if (i - this.f5625h >= 5000) {
                    this.f5621d.mo3713d(this.f5618a, m7316a(C2326b.TIME, i));
                    this.f5625h = this.f5624g;
                    this.f5623f.m6125a();
                    return;
                }
            }
            if (z) {
                this.f5621d.mo3713d(this.f5618a, m7316a(C2326b.TIME, i));
            }
        }
    }

    private void m7319a(Map<String, String> map) {
        map.put("exoplayer", String.valueOf(this.f5622e.mo3781g()));
        map.put("prep", Long.toString(this.f5622e.getInitialBufferTime()));
    }

    private void m7320a(Map<String, String> map, int i) {
        map.put("ptime", String.valueOf(((float) this.f5625h) / 1000.0f));
        map.put("time", String.valueOf(((float) i) / 1000.0f));
    }

    private void m7321b(Map<String, String> map) {
        C1944c b = this.f5623f.m6127b();
        C1943a b2 = b.m6139b();
        map.put("vwa", String.valueOf(b2.m6132c()));
        map.put("vwm", String.valueOf(b2.m6131b()));
        map.put("vwmax", String.valueOf(b2.m6133d()));
        map.put("vtime_ms", String.valueOf(b2.m6135f() * 1000.0d));
        map.put("mcvt_ms", String.valueOf(b2.m6136g() * 1000.0d));
        C1943a c = b.m6141c();
        map.put("vla", String.valueOf(c.m6132c()));
        map.put("vlm", String.valueOf(c.m6131b()));
        map.put("vlmax", String.valueOf(c.m6133d()));
        map.put("atime_ms", String.valueOf(c.m6135f() * 1000.0d));
        map.put("mcat_ms", String.valueOf(c.m6136g() * 1000.0d));
    }

    private Map<String, String> m7322c(int i) {
        boolean z = true;
        Map hashMap = new HashMap();
        boolean z2 = this.f5622e.getVideoStartReason() == C2222a.AUTO_STARTED;
        if (this.f5622e.mo3786i()) {
            z = false;
        }
        C2132u.m6829a(hashMap, z2, z);
        m7319a(hashMap);
        m7321b(hashMap);
        m7320a(hashMap, i);
        m7323c(hashMap);
        return hashMap;
    }

    private void m7323c(Map<String, String> map) {
        Rect rect = new Rect();
        this.f5622e.getGlobalVisibleRect(rect);
        map.put("pt", String.valueOf(rect.top));
        map.put("pl", String.valueOf(rect.left));
        map.put("ph", String.valueOf(this.f5622e.getMeasuredHeight()));
        map.put("pw", String.valueOf(this.f5622e.getMeasuredWidth()));
        WindowManager windowManager = (WindowManager) this.f5620c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        map.put("vph", String.valueOf(displayMetrics.heightPixels));
        map.put("vpw", String.valueOf(displayMetrics.widthPixels));
    }

    public void m7324a(int i) {
        m7318a(i, false);
    }

    public void m7325a(int i, int i2) {
        m7318a(i, true);
        this.f5625h = i2;
        this.f5624g = i2;
        this.f5623f.m6125a();
    }

    public void m7326b() {
        this.f5620c.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.f5626i);
    }

    public void m7327b(int i) {
        m7318a(i, true);
        this.f5625h = 0;
        this.f5624g = 0;
        this.f5623f.m6125a();
    }

    public void m7328c() {
        this.f5620c.getContentResolver().unregisterContentObserver(this.f5626i);
    }

    protected float m7329d() {
        return C2132u.m6828a(this.f5620c) * this.f5622e.getVolume();
    }

    public void m7330e() {
        if (((double) m7329d()) < 0.05d) {
            if (this.f5619b) {
                m7331f();
                this.f5619b = false;
            }
        } else if (!this.f5619b) {
            m7333h();
            this.f5619b = true;
        }
    }

    public void m7331f() {
        this.f5621d.mo3713d(this.f5618a, m7315a(C2326b.MUTE));
    }

    public Bundle mo3678g() {
        m7325a(m7337l(), m7337l());
        Bundle bundle = new Bundle();
        bundle.putInt("lastProgressTimeMS", this.f5624g);
        bundle.putInt("lastBoundaryTimeMS", this.f5625h);
        bundle.putBundle("adQualityManager", this.f5623f.mo3678g());
        return bundle;
    }

    public void m7333h() {
        this.f5621d.mo3713d(this.f5618a, m7315a(C2326b.UNMUTE));
    }

    public void m7334i() {
        this.f5621d.mo3713d(this.f5618a, m7315a(C2326b.SKIP));
    }

    public void m7335j() {
        this.f5621d.mo3713d(this.f5618a, m7315a(C2326b.PAUSE));
    }

    public void m7336k() {
        this.f5621d.mo3713d(this.f5618a, m7315a(C2326b.RESUME));
    }

    public int m7337l() {
        return this.f5624g;
    }
}
