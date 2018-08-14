package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.publisher.ei.a;
import com.vungle.publisher.ei.b;
import com.vungle.publisher.env.C1614r;
import com.vungle.publisher.env.i;
import com.vungle.publisher.log.Logger;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
class ge {
    b<?> f2965a;
    String f2966b;
    Integer f2967c;
    @Inject
    i f2968d;
    @Inject
    C1614r f2969e;

    @Singleton
    /* compiled from: vungle */
    static class C1624a {
        @Inject
        Provider<ge> f2964a;

        @Inject
        C1624a() {
        }

        ge m4013a(b<?> bVar) {
            ge geVar = (ge) this.f2964a.get();
            geVar.f2965a = bVar;
            return geVar;
        }
    }

    @Inject
    ge() {
    }

    String m4014a() {
        return this.f2965a.f();
    }

    String m4021b() {
        return this.f2965a.d();
    }

    String m4022c() {
        return this.f2966b;
    }

    void m4018a(String str) {
        this.f2966b = str;
    }

    void m4017a(Integer num) {
        this.f2967c = num;
    }

    File m4023d() {
        String f = m4025f();
        return f == null ? null : new File(f);
    }

    boolean m4024e() {
        File d = m4023d();
        if (d == null) {
            Logger.w(Logger.PREPARE_TAG, "null " + this.f2965a.o() + " file for ad " + m4014a());
            return false;
        } else if (d.exists()) {
            Logger.v(Logger.PREPARE_TAG, d.getAbsolutePath() + " exists, " + d.length() + " bytes");
            return true;
        } else {
            Logger.w(Logger.PREPARE_TAG, d.getAbsolutePath() + " missing ");
            return false;
        }
    }

    String m4025f() {
        return qr.a(new Object[]{m4021b(), this.f2965a.a()});
    }

    b m4026g() {
        return this.f2965a.o();
    }

    int m4027h() {
        this.f2965a.r();
        return this.f2965a.s();
    }

    int m4028i() {
        File d = m4023d();
        return d == null ? 0 : (int) d.length();
    }

    final boolean m4029j() {
        boolean t = this.f2965a.t();
        if (t) {
            a aVar = a.c;
            Logger.i(Logger.PREPARE_TAG, m4026g() + " " + aVar + " for " + "ad_id" + " " + m4014a());
            this.f2965a.b(aVar);
        } else {
            if (this.f2969e.m3945a()) {
                Logger.i(Logger.PREPARE_TAG, "debug mode: post-processing failed for " + this.f2965a.B() + " - not deleting " + m4025f());
            } else {
                Logger.d(Logger.PREPARE_TAG, "post-processing failed for " + this.f2965a.B() + " - deleting " + m4025f());
                this.f2965a.r();
            }
            this.f2965a.b(a.a);
        }
        return t;
    }

    boolean m4030k() {
        return m4031l();
    }

    final boolean m4031l() throws qm {
        a aVar;
        String str = Logger.PREPARE_TAG;
        boolean u = this.f2965a.u();
        str = m4014a();
        b g = m4026g();
        if (u) {
            Logger.i(Logger.PREPARE_TAG, g + " verified for " + "ad_id" + " " + str);
            aVar = a.c;
        } else {
            Logger.w(Logger.PREPARE_TAG, g + " failed verification; reprocessing " + "ad_id" + " " + str);
            aVar = a.a;
        }
        this.f2965a.b(aVar);
        return u;
    }

    boolean m4032m() throws qm {
        return m4020a(true);
    }

    boolean m4020a(boolean z) throws qm {
        if (this.f2968d.l()) {
            String str = Logger.PREPARE_TAG;
            String a = m4014a();
            b g = m4026g();
            if (this.f2967c == null) {
                a = g + " " + "size" + " " + this.f2967c + " for " + "ad_id" + ": " + a;
                if (z) {
                    Logger.d(Logger.PREPARE_TAG, a);
                    return true;
                }
                Logger.w(Logger.PREPARE_TAG, a);
            } else {
                int i = m4028i();
                if (i == this.f2967c.intValue()) {
                    Logger.d(Logger.PREPARE_TAG, g + " disk size matched " + "size" + " " + this.f2967c + " for " + "ad_id" + ": " + a);
                    return true;
                }
                Logger.d(Logger.PREPARE_TAG, g + " disk size " + i + " failed to match " + "size" + " " + this.f2967c + " for " + "ad_id" + ": " + a);
                if (m4024e()) {
                    Logger.d(Logger.PREPARE_TAG, "ignoring " + g + " size mismatch - file exists");
                    return true;
                }
            }
            return false;
        }
        throw new qm();
    }

    boolean m4033n() {
        File d = m4023d();
        Logger.d(Logger.PREPARE_TAG, "deleting " + d);
        return d != null && d.delete();
    }

    void m4015a(ContentValues contentValues) {
        contentValues.put("url", this.f2966b);
        contentValues.put("size", this.f2967c);
    }

    void m4016a(Cursor cursor) {
        m4018a(ce.e(cursor, "url"));
        m4017a(ce.c(cursor, "size"));
    }

    void m4019a(StringBuilder stringBuilder) {
        dp.m3572a(stringBuilder, "url", this.f2966b);
        dp.m3572a(stringBuilder, "size", this.f2967c);
    }
}
