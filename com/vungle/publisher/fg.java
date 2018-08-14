package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.ds.C1600a;
import com.vungle.publisher.el.C1607a;
import com.vungle.publisher.fb.C1620a;
import com.vungle.publisher.jk.a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class fg extends jk<fg, fb, fh, el, ek> implements eg {
    @Inject
    C1621a f2956s;
    @Inject
    C1620a f2957w;
    ds f2958x;

    @Singleton
    /* compiled from: vungle */
    public static class C1621a extends a<fg, fb, fh, el, ek, wg> {
        @Inject
        C1607a f2952c;
        @Inject
        C1620a f2953e;
        @Inject
        Provider<fg> f2954f;
        @Inject
        C1600a f2955g;

        protected /* synthetic */ cn$a m3984a() {
            return m3994g();
        }

        protected /* synthetic */ cy.a m3991d() {
            return m3995h();
        }

        protected /* synthetic */ dp[] m3992d(int i) {
            return m3990c(i);
        }

        protected /* synthetic */ dp g_() {
            return m3996i();
        }

        @Inject
        protected C1621a() {
        }

        public m m3993e() {
            return m.a;
        }

        protected C1607a m3994g() {
            return this.f2952c;
        }

        protected C1620a m3995h() {
            return this.f2953e;
        }

        protected fg m3988a(fg fgVar, Cursor cursor, boolean z) {
            super.a(fgVar, cursor, z);
            fgVar.o = ce.d(cursor, "download_end_millis");
            return fgVar;
        }

        protected fg m3989a(fg fgVar, el elVar, boolean z) {
            super.a(fgVar, elVar, z);
            fgVar.f2958x = this.f2955g.m3729a(fgVar);
            return fgVar;
        }

        protected fg[] m3990c(int i) {
            return new fg[i];
        }

        protected fg m3996i() {
            return (fg) this.f2954f.get();
        }
    }

    protected /* synthetic */ cy.a m4001a() {
        return m3998E();
    }

    protected /* synthetic */ C1592a b_() {
        return m3997D();
    }

    @Inject
    protected fg() {
    }

    protected C1621a m3997D() {
        return this.f2956s;
    }

    protected C1620a m3998E() {
        return this.f2957w;
    }

    public int m3999F() {
        return this.f2958x.m3730a();
    }

    public void a_(Long l) {
        this.f2958x.m3733b(l);
    }

    protected ContentValues m4000a(boolean z) {
        ContentValues a = super.a(z);
        this.f2958x.m3731a(a);
        return a;
    }

    public StringBuilder m4002p() {
        StringBuilder p = super.p();
        a(p, "download_end_millis", this.o);
        return p;
    }
}
