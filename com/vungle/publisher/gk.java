package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.publisher.cn.c;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.gl.C1626a;
import com.vungle.publisher.li.C1651a;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class gk extends dq implements dr<gk> {
    String f2972w;
    @Inject
    C1625a f2973x;
    @Inject
    C1651a f2974y;

    @Singleton
    /* compiled from: vungle */
    public static class C1625a extends cn$a<gk, wj> implements ea<gk, wj> {
        @Inject
        Provider<gk> f2970e;
        @Inject
        C1626a f2971f;

        public /* bridge */ /* synthetic */ dr m4038a(String str, boolean z) {
            return (dr) super.m3526a((Object) str, z);
        }

        public /* synthetic */ eb m4043d() {
            return m4046f();
        }

        protected /* synthetic */ dp[] mo2962d(int i) {
            return m4042c(i);
        }

        protected /* synthetic */ dp g_() {
            return m4045e();
        }

        @Inject
        C1625a() {
        }

        public gk m4040a(wj wjVar) {
            gk gkVar = (gk) super.mo2989a((wc) wjVar);
            gkVar.f2972w = wjVar.n();
            gkVar.a(c.a);
            return gkVar;
        }

        protected gk m4039a(gk gkVar, Cursor cursor, boolean z) {
            super.mo2988a((cn) gkVar, cursor, z);
            gkVar.f2972w = ce.e(cursor, "html_content");
            return gkVar;
        }

        protected m mo2990a() {
            return m.d;
        }

        protected gk m4045e() {
            return (gk) this.f2970e.get();
        }

        protected gk[] m4042c(int i) {
            return new gk[i];
        }

        public cn$a<gk, wj> n_() {
            return this;
        }

        public int mo2957a(List<gk> list) {
            return m4046f().m3750a(list);
        }

        public gl m4046f() {
            return (gl) this.f2971f.a(this);
        }
    }

    public /* synthetic */ cn$a m4048a() {
        return m4052t();
    }

    protected /* synthetic */ C1592a b_() {
        return m4053u();
    }

    public /* synthetic */ String m4050d() {
        return (String) super.c_();
    }

    public /* synthetic */ cn m_() {
        return m4055w();
    }

    public /* synthetic */ lf m4051s() {
        return m4054v();
    }

    @Inject
    gk() {
    }

    public C1625a m4052t() {
        return this.f2973x;
    }

    protected C1625a m4053u() {
        return m4052t();
    }

    public li m4054v() {
        return this.f2974y.m4334a(this.f2972w);
    }

    protected boolean m4049b() {
        return true;
    }

    protected ContentValues m4047a(boolean z) {
        ContentValues a = super.a(z);
        a.put("html_content", this.f2972w);
        return a;
    }

    public gk m4055w() {
        return this;
    }

    public boolean l_() {
        return true;
    }

    public List<gd<gk>> k_() {
        return new ArrayList();
    }
}
