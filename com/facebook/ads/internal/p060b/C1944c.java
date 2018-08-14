package com.facebook.ads.internal.p060b;

import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.io.Serializable;

public class C1944c implements Serializable {
    private C1943a f4504a;
    private C1943a f4505b;

    public static class C1943a implements Serializable {
        private double f4493a;
        private double f4494b;
        private double f4495c;
        private double f4496d;
        private double f4497e;
        private double f4498f;
        private double f4499g;
        private int f4500h;
        private double f4501i;
        private double f4502j;
        private double f4503k;

        public C1943a(double d) {
            this.f4497e = d;
        }

        public void m6129a() {
            this.f4493a = 0.0d;
            this.f4495c = 0.0d;
            this.f4496d = 0.0d;
            this.f4498f = 0.0d;
            this.f4500h = 0;
            this.f4501i = 0.0d;
            this.f4502j = MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE;
            this.f4503k = 0.0d;
        }

        public void m6130a(double d, double d2) {
            this.f4500h++;
            this.f4501i += d;
            this.f4495c = d2;
            this.f4503k += d2 * d;
            this.f4493a = this.f4503k / this.f4501i;
            this.f4502j = Math.min(this.f4502j, d2);
            this.f4498f = Math.max(this.f4498f, d2);
            if (d2 >= this.f4497e) {
                this.f4496d += d;
                this.f4494b += d;
                this.f4499g = Math.max(this.f4499g, this.f4494b);
                return;
            }
            this.f4494b = 0.0d;
        }

        public double m6131b() {
            return this.f4500h == 0 ? 0.0d : this.f4502j;
        }

        public double m6132c() {
            return this.f4493a;
        }

        public double m6133d() {
            return this.f4498f;
        }

        public double m6134e() {
            return this.f4501i;
        }

        public double m6135f() {
            return this.f4496d;
        }

        public double m6136g() {
            return this.f4499g;
        }
    }

    public C1944c() {
        this(0.5d, 0.05d);
    }

    public C1944c(double d) {
        this(d, 0.05d);
    }

    public C1944c(double d, double d2) {
        this.f4504a = new C1943a(d);
        this.f4505b = new C1943a(d2);
        m6137a();
    }

    void m6137a() {
        this.f4504a.m6129a();
        this.f4505b.m6129a();
    }

    void m6138a(double d, double d2) {
        this.f4504a.m6130a(d, d2);
    }

    public C1943a m6139b() {
        return this.f4504a;
    }

    void m6140b(double d, double d2) {
        this.f4505b.m6130a(d, d2);
    }

    public C1943a m6141c() {
        return this.f4505b;
    }
}
