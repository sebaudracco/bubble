package com.vungle.publisher;

import android.database.Cursor;
import android.database.SQLException;
import com.vungle.publisher.cn.c;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.dw.C1601a;
import com.vungle.publisher.ei.b;
import com.vungle.publisher.ek.C1606a;
import com.vungle.publisher.em.C1608a;
import com.vungle.publisher.er.C1617a;
import com.vungle.publisher.fq.C1623a;
import com.vungle.publisher.jh.a;
import com.vungle.publisher.log.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class el extends jh<el, ek, wg> implements dr<el> {
    em f2875w;
    public dw f2876x;
    boolean f2877y;
    @Inject
    C1607a f2878z;

    @Singleton
    /* compiled from: vungle */
    public static class C1607a extends a<el, ek, wg> implements ea<el, wg> {
        @Inject
        Provider<String> f2862e;
        @Inject
        C1608a f2863f;
        @Inject
        Provider<el> f2864g;
        @Inject
        C1606a f2865h;
        @Inject
        C1617a f2866i;
        @Inject
        C1601a f2867j;
        @Inject
        C1623a f2868k;

        public /* synthetic */ eb m3828d() {
            return m3834i();
        }

        protected /* synthetic */ dp[] m3829d(int i) {
            return m3827c(i);
        }

        protected /* synthetic */ dp g_() {
            return m3832g();
        }

        protected /* synthetic */ je.a m3835j() {
            return m3831f();
        }

        protected /* synthetic */ jg.a m3836k() {
            return m3830e();
        }

        public /* synthetic */ cn$a n_() {
            return m3833h();
        }

        @Inject
        C1607a() {
        }

        protected C1606a m3830e() {
            return this.f2865h;
        }

        protected C1623a m3831f() {
            return this.f2868k;
        }

        public el m3822a(wg wgVar) throws qm {
            el elVar = (el) super.a(wgVar);
            elVar.a((String) this.f2862e.get());
            elVar.f2875w = this.f2863f.m3860a(elVar, wgVar, b.b);
            elVar.f2876x = this.f2867j.m3734a(elVar);
            elVar.m3848a(c.a);
            return elVar;
        }

        protected m m3826a() {
            return m.a;
        }

        public int m3816a(List<el> list) {
            return m3834i().m3750a(list);
        }

        protected el m3821a(el elVar, Cursor cursor, boolean z) {
            super.a(elVar, cursor, z);
            elVar.a(ce.e(cursor, "parent_path"));
            elVar.f2876x = this.f2867j.m3734a(elVar);
            if (z) {
                m3823a(elVar, z);
            }
            return elVar;
        }

        em m3823a(el elVar, boolean z) {
            if (elVar.f2877y) {
                return elVar.f2875w;
            }
            em emVar = (em) this.f2863f.m3698a((String) elVar.u, b.b, z);
            elVar.f2875w = emVar;
            elVar.f2877y = true;
            return emVar;
        }

        protected el[] m3827c(int i) {
            return new el[i];
        }

        protected el m3832g() {
            return (el) this.f2864g.get();
        }

        public C1607a m3833h() {
            return this;
        }

        public er m3834i() {
            return (er) this.f2866i.a(this);
        }
    }

    public /* synthetic */ cn$a mo2979a() {
        return m3853u();
    }

    protected /* synthetic */ C1592a b_() {
        return m3851s();
    }

    public /* synthetic */ String m3850d() {
        return (String) super.c_();
    }

    public /* synthetic */ Object d_() {
        return mo2982w();
    }

    public /* synthetic */ cn m_() {
        return m3852t();
    }

    public /* synthetic */ a mo2983x() {
        return m3853u();
    }

    @Inject
    protected el() {
    }

    protected C1607a m3851s() {
        return m3853u();
    }

    public el m3852t() {
        return this;
    }

    public C1607a m3853u() {
        return this.f2878z;
    }

    public em m3854v() {
        return this.f2878z.m3823a(this, false);
    }

    public void m3848a(c cVar) {
        c cVar2 = this.f;
        super.a(cVar);
        this.f2876x.m3735a(cVar2, cVar);
    }

    public List<gd<el>> k_() {
        List<gd<el>> arrayList = new ArrayList();
        ek ekVar = (ek) m3837D();
        if (ekVar != null) {
            arrayList.add(ekVar);
        }
        em v = m3854v();
        if (v != null) {
            arrayList.add(v);
        }
        return arrayList;
    }

    public boolean l_() {
        boolean z;
        boolean z2 = false;
        String str = Logger.PREPARE_TAG;
        ek ekVar = (ek) m3837D();
        em v = m3854v();
        boolean z3 = ekVar != null;
        if (v != null) {
            z = true;
        } else {
            z = false;
        }
        if (z3 || z) {
            z2 = true;
        }
        String B = B();
        if (z2) {
            if (z3) {
                Logger.v(Logger.PREPARE_TAG, B + " has " + b.a + ": " + ekVar.m3800e());
            }
            if (z) {
                Logger.v(Logger.PREPARE_TAG, B + " has " + b.b + ": " + v.m3769e());
            }
        } else {
            m3848a(c.c);
        }
        return z2;
    }

    public boolean m3849b() {
        return qr.a(h());
    }

    public String mo2982w() throws SQLException {
        String w = super.mo2982w();
        if (this.f2875w != null) {
            this.f2875w.d_();
        }
        return w;
    }

    public int f_() {
        int f_ = super.f_();
        if (f_ == 1 && this.f2875w != null) {
            this.f2875w.f_();
        }
        return f_;
    }
}
