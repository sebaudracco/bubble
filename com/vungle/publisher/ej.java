package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.publisher.df.C1598a;
import com.vungle.publisher.ge.C1624a;
import com.vungle.publisher.ge.b;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.qu.a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class ej<A extends cn, V extends ej<A, V>> extends jm<A> implements b<A> {
    df[] f2849a;
    boolean f2850b;
    boolean f2851c;
    boolean f2852d;
    @Inject
    C1598a f2853e;
    @Inject
    ge f2854f;

    /* compiled from: vungle */
    static abstract class C1605a<A extends cn, V extends ej<A, V>, R extends wc> extends jm$a<A, V, R> {
        @Inject
        C1624a f2848a;

        protected C1605a() {
        }

        V mo2984a(A a, R r, ei.b bVar) {
            ej ejVar = (ej) super.m3695a((cn) a, (wc) r);
            if (ejVar != null) {
                ejVar.r = bVar;
            }
            return ejVar;
        }

        protected V m3755a(V v, Cursor cursor, boolean z) {
            super.mo2978a((jm) v, cursor, z);
            v.f2854f.m4016a(cursor);
            if (z) {
                v.m3781w();
            }
            return v;
        }
    }

    public String m3765a() {
        return o() + "." + m3780v();
    }

    public String m3769e() {
        return this.f2854f.m4022c();
    }

    public void m3767a(String str) {
        this.f2854f.m4018a(str);
    }

    public void m3766a(Integer num) {
        this.f2854f.m4017a(num);
    }

    public File m3770h() {
        return this.f2854f.m4023d();
    }

    public String m3780v() {
        return "zip";
    }

    private final ej<A, V> mo2985I() {
        return this;
    }

    public df[] m3781w() {
        if (!this.f2850b) {
            m3768a(this.f2853e.m3678a(mo2985I()), false);
        }
        return this.f2849a;
    }

    void m3768a(df[] dfVarArr, boolean z) {
        this.f2849a = dfVarArr;
        this.f2851c = z;
        this.f2850b = true;
    }

    File m3782x() {
        return new File(m3783z());
    }

    String m3783z() {
        return qr.a(new Object[]{d(), this.r});
    }

    public File m3759D() {
        return new File(mo2996E());
    }

    public String mo2996E() {
        return qr.a(new Object[]{m3783z(), "index.html"});
    }

    public String m3771i() {
        return this.f2854f.m4025f();
    }

    public boolean m3772m() {
        return this.f2854f.m4029j();
    }

    public boolean m3778t() {
        if (this.f2854f.m4032m() && m3761F()) {
            return m3779u();
        }
        return false;
    }

    boolean m3761F() {
        String str = Logger.PREPARE_TAG;
        File h = m3770h();
        try {
            List arrayList = new ArrayList();
            qu.a(h, m3782x(), new a[]{new 1(this, arrayList)});
            m3768a((df[]) arrayList.toArray(new df[arrayList.size()]), true);
            return true;
        } catch (Throwable e) {
            Logger.e(Logger.PREPARE_TAG, "error extracting " + h, e);
            return false;
        }
    }

    public boolean m3773n() {
        return this.f2854f.m4031l();
    }

    public boolean m3779u() {
        df[] w = m3781w();
        int length = w.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            if (!w[i].m3693r()) {
                return false;
            }
            i++;
            z = true;
        }
        return z;
    }

    public int m3775q() {
        m3763H();
        return this.f2854f.m4027h();
    }

    public boolean m3776r() {
        return m3762G() & m3763H();
    }

    boolean m3762G() {
        return this.f2854f.m4033n();
    }

    boolean m3763H() {
        String z = m3783z();
        Logger.d(Logger.DATABASE_TAG, "deleting " + this.r + " directory " + z);
        boolean a = qr.a(m3783z());
        if (a) {
            Logger.v(Logger.DATABASE_TAG, "deleting " + this.r + " directory " + z);
            this.f2849a = null;
            this.f2852d = true;
        } else {
            Logger.w(Logger.DATABASE_TAG, "failed to delete " + this.r + " directory " + z);
        }
        return a;
    }

    public int m3777s() {
        return super.q();
    }

    public int f_() {
        int f_ = super.f_();
        if (f_ == 1) {
            if (this.f2852d) {
                this.f2853e.m3669a((Integer) this.u);
                m3763H();
                Logger.v(Logger.DATABASE_TAG, "resetting areArchiveEntriesDeleted = false");
                this.f2852d = false;
            } else if (this.f2851c) {
                this.f2853e.mo2968a(this.f2849a);
                Logger.v(Logger.DATABASE_TAG, "resetting areArchiveEntriesNew = false");
                this.f2851c = false;
            }
        }
        return f_;
    }

    protected ContentValues m3764a(boolean z) {
        ContentValues a = super.a(z);
        this.f2854f.m4015a(a);
        return a;
    }

    protected StringBuilder m3774p() {
        StringBuilder p = super.p();
        this.f2854f.m4019a(p);
        return p;
    }
}
