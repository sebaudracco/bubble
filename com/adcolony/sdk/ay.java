package com.adcolony.sdk;

import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.util.ArrayList;

class ay {
    an f739a;
    ad f740b = new ad().m675b();
    float[] f741c = new float[16];
    ad f742d = new ad().m675b();
    ad f743e = new ad().m675b();
    ad f744f = new ad().m675b();
    ArrayList<ad> f745g = new ArrayList();
    ArrayList<ad> f746h = new ArrayList();
    boolean f747i;
    boolean f748j;
    boolean f749k = true;

    ay(an anVar) {
        this.f739a = anVar;
    }

    void m850a() {
        this.f739a.m750d();
        this.f743e.m675b();
        this.f747i = true;
    }

    void m856b() {
        m850a();
        do {
        } while (m860d());
    }

    ad m859c() {
        int size = this.f746h.size();
        if (size == 0) {
            return new ad();
        }
        return (ad) this.f746h.remove(size - 1);
    }

    boolean m860d() {
        int size = this.f745g.size();
        if (size == 0) {
            return false;
        }
        this.f739a.m750d();
        this.f748j = true;
        this.f746h.add(this.f745g.remove(size - 1));
        this.f743e.m675b();
        return true;
    }

    void m861e() {
        this.f739a.m750d();
        this.f745g.add(m859c().m679b(this.f743e));
        this.f743e.m675b();
        this.f748j = true;
        this.f747i = true;
    }

    void m853a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        double d10;
        double d11;
        double d12;
        double d13;
        this.f739a.m750d();
        double d14 = d3 / d8;
        double d15 = d4 / d9;
        if (d14 >= 0.0d) {
            d10 = d14;
            d11 = MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE;
            Object obj = null;
        } else {
            d10 = -d14;
            d11 = -1.0d;
            int i = 1;
        }
        Object obj2;
        if (d15 >= 0.0d) {
            obj2 = null;
            d12 = d15;
            d13 = MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE;
        } else {
            obj2 = 1;
            d12 = -d15;
            d13 = -1.0d;
        }
        d14 = d5 * d3;
        d15 = d6 * d4;
        if (!(obj == null && r6 == null)) {
            d14 -= d3 / 2.0d;
            d15 -= d4 / 2.0d;
            m858b((-d3) / 2.0d, (-d4) / 2.0d);
        }
        double d16 = d14;
        double cos = Math.cos(d7);
        double sin = Math.sin(d7);
        double d17 = (sin * d10) * d11;
        d13 *= cos * d12;
        this.f743e.m669a((cos * d10) * d11, d17, 0.0d, 0.0d, ((-sin) * d12) * d13, d13, 0.0d, 0.0d, 0.0d, 0.0d, MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE, 0.0d, (d - ((d10 * d16) * cos)) + ((sin * d12) * d15), (d2 - ((d16 * d10) * sin)) - (d15 * (d12 * cos)), 0.0d, MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
    }

    void m851a(double d) {
        this.f739a.m750d();
        this.f743e.m676b(d);
    }

    void m857b(double d) {
        this.f739a.m750d();
        this.f743e.m667a(d);
    }

    void m852a(double d, double d2) {
        this.f739a.m750d();
        this.f743e.m668a(d, d2, (double) MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
    }

    void m854a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16) {
        this.f739a.m750d();
        this.f743e.m678b(d, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16);
        this.f747i = true;
    }

    void m855a(ad adVar) {
        this.f739a.m750d();
        this.f742d.m679b(adVar);
        this.f749k = true;
    }

    void m862f() {
        m856b();
    }

    void m858b(double d, double d2) {
        this.f743e.m677b(d, d2, 0.0d);
    }

    void m863g() {
        if (this.f748j || this.f749k) {
            this.f749k = false;
            if (this.f748j) {
                this.f748j = false;
                this.f744f.m675b();
                for (int size = this.f745g.size() - 1; size >= 0; size--) {
                    this.f744f.m672a((ad) this.f745g.get(size));
                }
            }
            this.f740b.m675b();
            this.f740b.m672a(this.f743e);
            this.f740b.m672a(this.f744f);
            this.f740b.m672a(this.f742d);
            this.f740b.m674a(this.f741c);
        }
    }
}
