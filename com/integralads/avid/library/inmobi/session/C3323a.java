package com.integralads.avid.library.inmobi.session;

import android.app.Activity;
import android.view.View;
import com.integralads.avid.library.inmobi.C3304d;
import com.integralads.avid.library.inmobi.p123c.C3294a;
import com.integralads.avid.library.inmobi.session.internal.C3333a;
import java.util.UUID;

/* compiled from: AbstractAvidAdSession */
public abstract class C3323a<T extends View> {
    private String f8468a = UUID.randomUUID().toString();

    public String m11358a() {
        return this.f8468a;
    }

    public void m11360a(T t, Activity activity) {
        C3333a a = C3304d.m11239b().m11244a(this.f8468a);
        if (a != null) {
            a.m11392a((View) t);
        }
        C3304d.m11239b().m11246a(activity);
    }

    public void m11359a(T t) {
        C3333a a = C3304d.m11239b().m11244a(this.f8468a);
        if (a != null) {
            a.m11397b((View) t);
        }
    }

    public void m11361b() {
        C3333a a = C3304d.m11239b().m11244a(m11358a());
        if (a != null) {
            a.mo6360l();
        }
    }

    public C3294a m11363c() {
        C3333a a = C3304d.m11239b().m11244a(m11358a());
        C3294a e = a != null ? a.m11402e() : null;
        if (e != null) {
            return e;
        }
        throw new IllegalStateException("The AVID ad session is not deferred. Please ensure you are only using AvidDeferredAdSessionListener for deferred AVID ad session.");
    }

    public void m11362b(View view) {
        C3333a a = C3304d.m11239b().m11244a(m11358a());
        if (a != null) {
            a.m11407j().m11434a(view);
        }
    }
}
