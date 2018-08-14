package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.ei.b;
import com.vungle.publisher.hr.C1632a;
import com.vungle.publisher.jg.a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class hq extends jg<hr> {
    @Inject
    C1632a f2991a;
    @Inject
    C1631a f2992b;
    String f2993c;

    @Singleton
    /* compiled from: vungle */
    protected static class C1631a extends a<hr, hq, wm> {
        private static final b f2989b = b.c;
        @Inject
        Provider<hq> f2990a;

        protected /* synthetic */ Object[] m4113b(int i) {
            return m4114c(i);
        }

        protected /* synthetic */ dp[] m4116d(int i) {
            return m4112a(i);
        }

        protected /* synthetic */ dp g_() {
            return m4115d();
        }

        @Inject
        protected C1631a() {
        }

        protected b m4105a() {
            return f2989b;
        }

        protected hq m4107a(hr hrVar, wm wmVar) {
            hq hqVar = (hq) super.a(hrVar, wmVar);
            if (hqVar != null) {
                hqVar.f2993c = wmVar.x();
                hqVar.r = f2989b;
            }
            return hqVar;
        }

        protected hq m4106a(hq hqVar, Cursor cursor, boolean z) {
            super.a(hqVar, cursor, z);
            hqVar.f2993c = ce.e(cursor, "url");
            return hqVar;
        }

        protected hq[] m4112a(int i) {
            return new hq[i];
        }

        protected Integer[] m4114c(int i) {
            return new Integer[i];
        }

        protected hq m4115d() {
            return (hq) this.f2990a.get();
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m4118a();
    }

    protected /* synthetic */ cn$a m4122y() {
        return m4119e();
    }

    @Inject
    protected hq() {
    }

    protected C1631a m4118a() {
        return this.f2992b;
    }

    protected C1632a m4119e() {
        return this.f2991a;
    }

    public Uri m4121x() {
        return Uri.parse(this.f2993c);
    }

    protected ContentValues m4117a(boolean z) {
        ContentValues a = super.a(z);
        a.put("url", this.f2993c);
        return a;
    }

    protected StringBuilder m4120p() {
        StringBuilder p = super.p();
        a(p, "url", this.f2993c);
        return p;
    }
}
