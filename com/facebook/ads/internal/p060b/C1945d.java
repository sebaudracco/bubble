package com.facebook.ads.internal.p060b;

import android.os.Bundle;
import com.facebook.ads.internal.p056q.p057a.C1912o;

public class C1945d implements C1912o<Bundle> {
    private C1944c f4506a;
    private final C1944c f4507b;
    private final C1909b f4508c;
    private boolean f4509d = false;
    private boolean f4510e = false;
    private boolean f4511f = false;

    public C1945d(C1909b c1909b) {
        this.f4508c = c1909b;
        this.f4507b = new C1944c(c1909b.f4295b);
        this.f4506a = new C1944c(c1909b.f4295b);
    }

    public C1945d(C1909b c1909b, Bundle bundle) {
        this.f4508c = c1909b;
        this.f4507b = (C1944c) bundle.getSerializable("testStats");
        this.f4506a = (C1944c) bundle.getSerializable("viewableStats");
        this.f4509d = bundle.getBoolean("ended");
        this.f4510e = bundle.getBoolean("passed");
        this.f4511f = bundle.getBoolean("complete");
    }

    private void m6142a() {
        this.f4510e = true;
        m6143b();
    }

    private void m6143b() {
        this.f4511f = true;
        m6144c();
    }

    private void m6144c() {
        this.f4509d = true;
        this.f4508c.mo3675a(this.f4511f, this.f4510e, this.f4510e ? this.f4506a : this.f4507b);
    }

    public void m6145a(double d, double d2) {
        if (!this.f4509d) {
            this.f4507b.m6138a(d, d2);
            this.f4506a.m6138a(d, d2);
            double f = this.f4506a.m6139b().m6135f();
            if (this.f4508c.f4298e && d2 < this.f4508c.f4295b) {
                this.f4506a = new C1944c(this.f4508c.f4295b);
            }
            if (this.f4508c.f4296c >= 0.0d && this.f4507b.m6139b().m6134e() > this.f4508c.f4296c && f == 0.0d) {
                m6143b();
            } else if (f >= this.f4508c.f4297d) {
                m6142a();
            }
        }
    }

    public Bundle mo3678g() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("viewableStats", this.f4506a);
        bundle.putSerializable("testStats", this.f4507b);
        bundle.putBoolean("ended", this.f4509d);
        bundle.putBoolean("passed", this.f4510e);
        bundle.putBoolean("complete", this.f4511f);
        return bundle;
    }
}
