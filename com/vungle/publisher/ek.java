package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.bgjd.ici.p030e.C1485h.C1484a;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.el.C1607a;
import com.vungle.publisher.ge.C1624a;
import com.vungle.publisher.ge.b;
import com.vungle.publisher.jg.a;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class ek extends jg<el> implements b<el> {
    String f2858a;
    ge f2859b;
    @Inject
    C1607a f2860c;
    @Inject
    C1606a f2861d;

    @Singleton
    /* compiled from: vungle */
    protected static class C1606a extends a<el, ek, wg> {
        private static final ei.b f2855c = ei.b.a;
        @Inject
        Provider<ek> f2856a;
        @Inject
        C1624a f2857b;

        protected /* synthetic */ dp[] m3795d(int i) {
            return m3793a(i);
        }

        protected /* synthetic */ dp g_() {
            return m3794d();
        }

        @Inject
        protected C1606a() {
        }

        protected ei.b m3786a() {
            return f2855c;
        }

        protected ek m3788a(el elVar, wg wgVar) {
            ek ekVar = (ek) super.a(elVar, wgVar);
            if (ekVar != null) {
                return m3784a(ekVar, wgVar);
            }
            return ekVar;
        }

        private ek m3784a(ek ekVar, wg wgVar) {
            ekVar.f2858a = wgVar.p();
            ekVar.m3798a(wgVar.o());
            ekVar.m3799a(wgVar.x());
            ekVar.r = f2855c;
            return ekVar;
        }

        protected ek m3787a(ek ekVar, Cursor cursor, boolean z) {
            super.a(ekVar, cursor, z);
            ekVar.f2859b.m4016a(cursor);
            ekVar.f2858a = ce.e(cursor, C1484a.f2396c);
            return ekVar;
        }

        protected ek[] m3793a(int i) {
            return new ek[i];
        }

        protected ek m3794d() {
            ek ekVar = (ek) this.f2856a.get();
            ekVar.f2859b = this.f2857b.m4013a(ekVar);
            return ekVar;
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m3801h();
    }

    protected /* synthetic */ cn$a m3814y() {
        return m3811v();
    }

    @Inject
    protected ek() {
    }

    protected C1606a m3801h() {
        return this.f2861d;
    }

    protected C1607a m3811v() {
        return this.f2860c;
    }

    public String m3797a() {
        return o() + "." + m3812w();
    }

    public String m3800e() {
        return this.f2859b.m4022c();
    }

    public void m3799a(String str) {
        this.f2859b.m4018a(str);
    }

    public void m3798a(Integer num) {
        this.f2859b.m4017a(num);
    }

    public String m3812w() {
        return "mp4";
    }

    public String m3802i() {
        return this.f2859b.m4025f();
    }

    public Uri m3813x() {
        return Uri.fromFile(new File(m3802i()));
    }

    public boolean m3803m() {
        return this.f2859b.m4029j();
    }

    public boolean m3809t() {
        return this.f2859b.m4030k();
    }

    public boolean m3804n() {
        return this.f2859b.m4031l();
    }

    public boolean m3810u() {
        return this.f2859b.m4032m() && m3815z();
    }

    boolean m3815z() {
        return true;
    }

    public int m3806q() {
        return this.f2859b.m4027h();
    }

    public boolean m3807r() {
        return this.f2859b.m4033n();
    }

    public int m3808s() {
        return super.q();
    }

    protected ContentValues m3796a(boolean z) {
        ContentValues a = super.a(z);
        this.f2859b.m4015a(a);
        a.put(C1484a.f2396c, this.f2858a);
        return a;
    }

    protected StringBuilder m3805p() {
        StringBuilder p = super.p();
        this.f2859b.m4019a(p);
        a(p, C1484a.f2396c, this.f2858a);
        return p;
    }
}
