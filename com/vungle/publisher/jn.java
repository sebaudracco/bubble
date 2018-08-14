package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.vungle.publisher.cn.c;
import com.vungle.publisher.dk.C1599a;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.dw.C1601a;
import com.vungle.publisher.ei.b;
import com.vungle.publisher.iz.C1641a;
import com.vungle.publisher.jo.C1643a;
import com.vungle.publisher.jt.C1644a;
import com.vungle.publisher.jy.C1645a;
import com.vungle.publisher.ks.C1649a;
import com.vungle.publisher.lb.C1650a;
import com.vungle.publisher.log.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public class jn extends dq implements dr<jn> {
    jo f3038A;
    List<iz> f3039B;
    ks f3040C;
    dw f3041D;
    @Inject
    C1642a f3042E;
    @Inject
    C1650a f3043F;
    boolean f3044w = false;
    boolean f3045x = false;
    boolean f3046y;
    List<dk> f3047z;

    @Singleton
    /* compiled from: vungle */
    public static class C1642a extends cn$a<jn, wr> implements ea<jn, wr> {
        @Inject
        Provider<jn> f3028e;
        @Inject
        zl f3029f;
        @Inject
        C1599a f3030g;
        @Inject
        Provider<String> f3031h;
        @Inject
        C1644a f3032i;
        @Inject
        C1643a f3033j;
        @Inject
        C1645a f3034k;
        @Inject
        C1649a f3035l;
        @Inject
        C1641a f3036m;
        @Inject
        C1601a f3037n;

        public /* synthetic */ int mo2995b(cn cnVar, wc wcVar) {
            return m4225a((jn) cnVar, (wr) wcVar);
        }

        public /* synthetic */ eb m4238d() {
            return m4242g();
        }

        protected /* synthetic */ dp[] mo2962d(int i) {
            return m4237c(i);
        }

        protected /* synthetic */ dp g_() {
            return m4240e();
        }

        public /* synthetic */ cn$a n_() {
            return m4241f();
        }

        @Inject
        C1642a() {
        }

        public jn m4232a(wr wrVar) {
            jn jnVar = (jn) super.mo2989a((wc) wrVar);
            jnVar.m = wrVar.c();
            jnVar.a((String) this.f3031h.get());
            jnVar.f3047z = this.f3030g.m3709a(jnVar, wrVar);
            jnVar.f3038A = this.f3033j.m4262a(jnVar, wrVar, b.d);
            jnVar.f3040C = (ks) this.f3035l.a(jnVar, wrVar);
            jnVar.f3041D = this.f3037n.m3734a(jnVar);
            jnVar.k = wrVar.r();
            jnVar.s = wrVar.m();
            jnVar.f3046y = wrVar.n();
            JSONObject o = wrVar.o();
            if (o != null) {
                jnVar.f3039B = this.f3036m.m4212a((String) jnVar.u, o);
            }
            jnVar.m4247a(c.a);
            return jnVar;
        }

        public int m4225a(jn jnVar, wr wrVar) {
            m3545a((cn) jnVar, (wc) wrVar);
            jnVar.m = wrVar.c();
            jnVar.f3040C.a(wrVar);
            return super.mo2995b(jnVar, wrVar);
        }

        public int mo2957a(List<jn> list) {
            return m4242g().m3750a(list);
        }

        protected jn m4231a(jn jnVar, Cursor cursor, boolean z) {
            super.mo2988a((cn) jnVar, cursor, z);
            jnVar.l = ce.a(cursor, "delete_local_content_attempts", 0);
            jnVar.m = ce.d(cursor, "expiration_timestamp_seconds");
            jnVar.a(ce.e(cursor, "parent_path"));
            jnVar.o = ce.a(cursor, "prepare_retry_count", 0);
            jnVar.p = this.f3029f.m4931a();
            jnVar.f3040C = (ks) this.f3035l.a(jnVar);
            jnVar.f3041D = this.f3037n.m3734a(jnVar);
            jnVar.k = ce.e(cursor, "template_id");
            jnVar.s = ce.e(cursor, "template_type");
            jnVar.f3046y = ce.a(cursor, "requires_sideloading").booleanValue();
            if (z) {
                m4236b(jnVar, z);
                m4233a(jnVar, z);
            }
            return jnVar;
        }

        jo m4233a(jn jnVar, boolean z) {
            if (jnVar.f3045x) {
                return jnVar.f3038A;
            }
            jo joVar = (jo) this.f3033j.m3698a((String) jnVar.u, b.d, z);
            jnVar.f3038A = joVar;
            jnVar.f3045x = true;
            return joVar;
        }

        List<dk> m4236b(jn jnVar, boolean z) {
            if (jnVar.f3044w) {
                return jnVar.f3047z;
            }
            List<dk> a = this.f3030g.m3699a(b.e, (String) jnVar.u, z);
            jnVar.f3047z = a;
            jnVar.f3044w = true;
            return a;
        }

        protected m mo2990a() {
            return m.c;
        }

        protected jn m4240e() {
            return (jn) this.f3028e.get();
        }

        protected jn[] m4237c(int i) {
            return new jn[i];
        }

        public C1642a m4241f() {
            return this;
        }

        public jt m4242g() {
            return (jt) this.f3032i.a(this);
        }
    }

    public /* synthetic */ cn$a m4245a() {
        return m4252t();
    }

    protected /* synthetic */ C1592a b_() {
        return m4253u();
    }

    public /* synthetic */ String m4249d() {
        return (String) super.c_();
    }

    public /* synthetic */ Object d_() {
        return m4257y();
    }

    public /* synthetic */ cn m_() {
        return m4258z();
    }

    public /* synthetic */ lf m4251s() {
        return m4254v();
    }

    @Inject
    jn() {
    }

    public C1642a m4252t() {
        return this.f3042E;
    }

    protected C1642a m4253u() {
        return m4252t();
    }

    public lb m4254v() {
        m4252t().m4233a(this, false);
        m4243D();
        return this.f3043F.m4326a(this.f3038A.m3759D().toURI().toString());
    }

    private void m4243D() {
        FileOutputStream fileOutputStream;
        Throwable e;
        String a = ru.a();
        try {
            File file = new File(qr.a(new Object[]{this.f3038A.m3783z(), "mraid.js"}));
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file, true);
            try {
                fileOutputStream.write(("\n" + a + "\n").getBytes(Charset.forName("UTF-8")));
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable e2) {
                        Logger.d(Logger.DATABASE_TAG, "error closing output file", e2);
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    Logger.e(Logger.DATABASE_TAG, "Failed writing to the mraid js file", e2);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e22) {
                            Logger.d(Logger.DATABASE_TAG, "error closing output file", e22);
                        }
                    }
                } catch (Throwable th) {
                    e22 = th;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e4) {
                            Logger.d(Logger.DATABASE_TAG, "error closing output file", e4);
                        }
                    }
                    throw e22;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            fileOutputStream = null;
            Logger.e(Logger.DATABASE_TAG, "Failed writing to the mraid js file", e22);
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th2) {
            e22 = th2;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw e22;
        }
    }

    public boolean m4255w() {
        return this.f3046y;
    }

    public List<String> m4246a(jf jfVar) {
        return this.f3040C.a(jfVar);
    }

    public void m4256x() {
        if (this.f3040C != null) {
            this.f3040C.c();
        }
    }

    public boolean m4248b() {
        return qr.a(h());
    }

    public void m4247a(c cVar) {
        c cVar2 = this.f;
        super.a(cVar);
        this.f3041D.m3735a(cVar2, cVar);
    }

    public String m4257y() throws SQLException {
        String str = (String) super.d_();
        m4256x();
        if (this.f3047z != null) {
            for (dk x : this.f3047z) {
                x.m3727x();
            }
        }
        if (this.f3038A != null) {
            this.f3038A.d_();
        }
        if (this.f3039B != null) {
            for (iz d_ : this.f3039B) {
                d_.d_();
            }
        }
        return str;
    }

    public int f_() {
        int f_ = super.f_();
        if (f_ == 1) {
            if (this.f3047z != null) {
                for (dk f_2 : this.f3047z) {
                    f_2.f_();
                }
            }
            if (this.f3038A != null) {
                this.f3038A.f_();
            }
            if (this.f3039B != null) {
                for (iz f_3 : this.f3039B) {
                    f_3.f_();
                }
            }
        }
        return f_;
    }

    protected ContentValues m4244a(boolean z) {
        ContentValues a = super.a(z);
        a.put("requires_sideloading", Boolean.valueOf(this.f3046y));
        if (z) {
            a.put("template_id", this.k);
            a.put("template_type", this.s);
        }
        return a;
    }

    protected StringBuilder m4250p() {
        StringBuilder p = super.p();
        a(p, "template_id", this.k);
        a(p, "template_type", this.s);
        return p;
    }

    public jn m4258z() {
        return this;
    }

    public boolean l_() {
        boolean z = true;
        m4253u().m4233a(this, true);
        String str = Logger.PREPARE_TAG;
        if (this.f3038A == null) {
            z = false;
        }
        str = B();
        if (z) {
            Logger.v(Logger.PREPARE_TAG, str + " has " + b.d + ": " + this.f3038A.m3769e());
        } else {
            Logger.w(Logger.PREPARE_TAG, "vungle mraid ad is invalid. template = " + this.f3038A);
            m4247a(c.c);
        }
        return z;
    }

    public List<gd<jn>> k_() {
        List<gd<jn>> arrayList = new ArrayList();
        C1642a u = m4253u();
        u.m4236b(this, true);
        if (this.f3047z != null) {
            arrayList.addAll(this.f3047z);
        } else {
            Logger.d(Logger.DATABASE_TAG, "vungle mraid ad assets are null");
        }
        u.m4233a(this, true);
        if (this.f3038A != null) {
            arrayList.add(this.f3038A);
        } else {
            Logger.w(Logger.DATABASE_TAG, "vungle mraid ad template is null");
        }
        return arrayList;
    }
}
