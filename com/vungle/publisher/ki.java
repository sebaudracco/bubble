package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.publisher.cy.a;
import com.vungle.publisher.cz.C1595a;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.ds.C1600a;
import com.vungle.publisher.jn.C1642a;
import com.vungle.publisher.kd.C1646a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class ki extends cz<ki, kd, kj, jn> implements eg {
    ds f3061s;
    String f3062w;
    @Inject
    C1647a f3063x;

    @Singleton
    /* compiled from: vungle */
    public static class C1647a extends C1595a<ki, kd, kj, jn, wr> {
        @Inject
        C1642a f3057c;
        @Inject
        C1646a f3058e;
        @Inject
        Provider<ki> f3059f;
        @Inject
        C1600a f3060g;

        protected /* synthetic */ cn$a mo2991a() {
            return m4305g();
        }

        protected /* synthetic */ a mo2992d() {
            return m4306h();
        }

        protected /* synthetic */ dp[] mo2962d(int i) {
            return m4301c(i);
        }

        protected /* synthetic */ dp g_() {
            return m4307i();
        }

        @Inject
        C1647a() {
        }

        public ki m4298a(jn jnVar) {
            ki kiVar = (ki) super.mo2997a((cn) jnVar);
            kiVar.f3061s = this.f3060g.m3729a(kiVar);
            kiVar.f3062w = jnVar.i();
            return kiVar;
        }

        protected ki m4299a(ki kiVar, Cursor cursor, boolean z) {
            super.mo2998a((cz) kiVar, cursor, z);
            kiVar.o = ce.d(cursor, "download_end_millis");
            kiVar.f3062w = ce.e(cursor, "template_id");
            return kiVar;
        }

        protected ki m4300a(ki kiVar, jn jnVar, boolean z) {
            super.mo2999a((cz) kiVar, (cn) jnVar, z);
            kiVar.f3061s = this.f3060g.m3729a(kiVar);
            kiVar.f3062w = ((jn) kiVar.a).i();
            return kiVar;
        }

        protected C1642a m4305g() {
            return this.f3057c;
        }

        protected C1646a m4306h() {
            return this.f3058e;
        }

        public m mo2993e() {
            return m.c;
        }

        protected ki m4307i() {
            return (ki) this.f3059f.get();
        }

        protected ki[] m4301c(int i) {
            return new ki[i];
        }
    }

    protected /* synthetic */ a mo2994a() {
        return m4308D();
    }

    protected /* synthetic */ C1592a b_() {
        return m4309E();
    }

    @Inject
    ki() {
    }

    protected C1646a m4308D() {
        return this.f3063x.m4306h();
    }

    protected C1647a m4309E() {
        return this.f3063x;
    }

    public int m4310F() {
        return this.f3061s.m3730a();
    }

    public void a_(Long l) {
        this.f3061s.m3733b(l);
    }

    protected ContentValues mo2964a(boolean z) {
        ContentValues a = super.mo2964a(z);
        this.f3061s.m3731a(a);
        if (z) {
            a.put("template_id", this.f3062w);
        }
        return a;
    }
}
