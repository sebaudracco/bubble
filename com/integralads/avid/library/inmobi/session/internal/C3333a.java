package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.inmobi.p123c.C3294a;
import com.integralads.avid.library.inmobi.p123c.C3295b;
import com.integralads.avid.library.inmobi.p126f.C3315d;
import com.integralads.avid.library.inmobi.p128h.C3322b;
import com.integralads.avid.library.inmobi.session.C3329g;
import com.integralads.avid.library.inmobi.session.internal.p129a.C3331a;
import com.integralads.avid.library.inmobi.session.internal.p129a.C3331a.C3330a;

/* compiled from: InternalAvidAdSession */
public abstract class C3333a<T extends View> implements C3330a {
    private final C3334b f8482a;
    private C3331a f8483b = new C3331a(this.f8482a);
    private C3322b<T> f8484c;
    private C3295b f8485d;
    private C3306c f8486e;
    private boolean f8487f;
    private boolean f8488g;
    private final C3340i f8489h;

    public abstract SessionType mo6357a();

    public abstract MediaType mo6358b();

    public abstract WebView mo6356u();

    public C3333a(Context context, String str, C3329g c3329g) {
        this.f8482a = new C3334b(context, str, mo6357a().toString(), mo6358b().toString(), c3329g);
        this.f8483b.m11380a((C3330a) this);
        this.f8484c = new C3322b(null);
        this.f8487f = !c3329g.m11370b();
        if (!this.f8487f) {
            this.f8485d = new C3295b(this, this.f8483b);
        }
        this.f8489h = new C3340i();
    }

    public String m11399c() {
        return this.f8482a.m11419a();
    }

    public T m11401d() {
        return (View) this.f8484c.m11354a();
    }

    public C3294a m11402e() {
        return this.f8485d;
    }

    public void m11393a(C3306c c3306c) {
        this.f8486e = c3306c;
    }

    public boolean m11403f() {
        return this.f8484c.m11356b();
    }

    public boolean m11404g() {
        return this.f8488g;
    }

    public boolean m11405h() {
        return this.f8487f;
    }

    public C3331a m11406i() {
        return this.f8483b;
    }

    public C3340i m11407j() {
        return this.f8489h;
    }

    public void m11392a(T t) {
        this.f8484c.m11355a(t);
        mo6354q();
        m11417t();
    }

    public void m11397b(T t) {
        if (m11400c(t)) {
            m11413p();
            this.f8484c.m11355a(null);
            mo6355r();
            m11417t();
        }
    }

    public boolean m11400c(View view) {
        return this.f8484c.m11357b(view);
    }

    public void mo6359k() {
    }

    public void mo6360l() {
        m11413p();
        if (this.f8485d != null) {
            this.f8485d.m11192a();
        }
        this.f8483b.m11386c();
        this.f8487f = false;
        m11417t();
        if (this.f8486e != null) {
            this.f8486e.mo6330a(this);
        }
    }

    public void m11410m() {
        this.f8487f = true;
        m11417t();
    }

    public void mo6353n() {
        m11417t();
    }

    public void m11395a(boolean z) {
        if (m11404g()) {
            this.f8483b.m11385b(z ? "active" : "inactive");
        }
    }

    public void m11394a(String str) {
        this.f8483b.m11381a(str);
    }

    public void m11412o() {
        m11413p();
    }

    protected void m11413p() {
        if (m11404g()) {
            this.f8483b.m11381a(C3315d.m11293a().toString());
        }
    }

    protected void mo6354q() {
    }

    protected void mo6355r() {
    }

    protected void m11416s() {
        this.f8483b.m11379a(mo6356u());
    }

    protected void m11417t() {
        boolean z = this.f8483b.m11383a() && this.f8487f && !m11403f();
        if (this.f8488g != z) {
            m11398b(z);
        }
    }

    protected void m11398b(boolean z) {
        this.f8488g = z;
        if (this.f8486e == null) {
            return;
        }
        if (z) {
            this.f8486e.mo6331b(this);
        } else {
            this.f8486e.mo6332c(this);
        }
    }
}
