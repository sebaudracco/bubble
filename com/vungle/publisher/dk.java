package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.ge.C1624a;
import com.vungle.publisher.ge.b;
import com.vungle.publisher.iz.C1641a;
import com.vungle.publisher.jn.C1642a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: vungle */
public class dk extends jm<jn> implements b<jn> {
    ge f2820a;
    String f2821b;
    String f2822c;
    iz f2823d;
    @Inject
    C1642a f2824e;
    @Inject
    C1599a f2825f;

    @Singleton
    /* compiled from: vungle */
    static class C1599a extends jm$a<jn, dk, wr> {
        @Inject
        Provider<dk> f2817a;
        @Inject
        C1624a f2818b;
        @Inject
        C1641a f2819c;

        protected /* synthetic */ dp[] mo2962d(int i) {
            return m3710a(i);
        }

        protected /* synthetic */ dp g_() {
            return m3703a();
        }

        @Inject
        C1599a() {
        }

        dk m3706a(jn jnVar, wr wrVar, wy wyVar) {
            dk dkVar;
            if (wrVar == null) {
                dkVar = null;
            } else if (wyVar != null) {
                dkVar = m3705a((dk) super.m3695a((cn) jnVar, (wc) wrVar), wyVar);
            } else {
                throw new IllegalArgumentException("cannot create asset with null url");
            }
            dkVar.f2823d = this.f2819c.m4210a((String) jnVar.c_(), dkVar.f2822c, dkVar.m3718i());
            return dkVar;
        }

        public List<dk> m3709a(jn jnVar, wr wrVar) {
            Collection<wy> a = wrVar.p().a();
            List arrayList = new ArrayList();
            for (wy a2 : a) {
                arrayList.add(m3706a(jnVar, wrVar, a2));
            }
            return arrayList;
        }

        protected dk m3704a(dk dkVar, Cursor cursor, boolean z) {
            super.mo2978a((jm) dkVar, cursor, z);
            dkVar.f2821b = ce.e(cursor, SchemaSymbols.ATTVAL_EXTENSION);
            dkVar.f2822c = ce.e(cursor, "name");
            dkVar.f2820a.m4016a(cursor);
            return dkVar;
        }

        dk m3705a(dk dkVar, wy wyVar) {
            dkVar.r = ei.b.e;
            String b = wyVar.b();
            String a = wyVar.a();
            String c = wyVar.c();
            if (b == null) {
                throw new IllegalArgumentException("asset object must have a non-null url");
            } else if (a == null) {
                throw new IllegalArgumentException("asset object must have a non-null extension");
            } else if (c == null) {
                throw new IllegalArgumentException("asset object must have a non-null name");
            } else {
                dkVar.f2822c = c;
                dkVar.f2821b = a;
                dkVar.m3715a(b);
                return dkVar;
            }
        }

        protected dk m3703a() {
            dk dkVar = (dk) this.f2817a.get();
            dkVar.f2820a = this.f2818b.m4013a(dkVar);
            return dkVar;
        }

        protected dk[] m3710a(int i) {
            return new dk[i];
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m3726w();
    }

    public /* synthetic */ Object d_() {
        return m3727x();
    }

    protected /* synthetic */ cn$a m3728y() {
        return m3725v();
    }

    @Inject
    dk() {
    }

    public String m3713a() {
        return this.f2822c + "." + m3717h();
    }

    public String m3716e() {
        return this.f2820a.m4022c();
    }

    public void m3715a(String str) {
        this.f2820a.m4018a(str);
    }

    public void m3714a(Integer num) {
        this.f2820a.m4017a(num);
    }

    public String m3717h() {
        return this.f2821b;
    }

    public String m3718i() {
        return this.f2820a.m4025f();
    }

    public boolean m3719m() {
        return this.f2820a.m4029j();
    }

    public boolean m3720n() {
        return this.f2820a.m4031l();
    }

    public boolean m3721r() {
        return this.f2820a.m4024e();
    }

    public int m3722s() {
        return super.q();
    }

    public boolean m3723t() {
        return true;
    }

    public boolean m3724u() {
        return true;
    }

    protected C1642a m3725v() {
        return this.f2824e;
    }

    protected C1599a m3726w() {
        return this.f2825f;
    }

    public Integer m3727x() {
        Integer num = (Integer) super.d_();
        if (this.f2823d != null) {
            this.f2823d.d_();
        }
        return num;
    }

    protected ContentValues m3712a(boolean z) {
        ContentValues a = super.a(z);
        this.f2820a.m4015a(a);
        a.put("name", this.f2822c);
        a.put(SchemaSymbols.ATTVAL_EXTENSION, this.f2821b);
        return a;
    }
}
