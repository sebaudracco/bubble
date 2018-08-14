package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import com.vungle.publisher.ei.b;
import com.vungle.publisher.jm.a;
import com.vungle.publisher.wp.C4263a;

/* compiled from: vungle */
public abstract class jg<A extends cn> extends jm<A> {
    Float f10530e;
    Integer f10531f;
    Integer f10532g;
    Boolean f10533h;
    Boolean f10534i;
    Integer f10535j;
    Integer f10536k;
    Integer f10537l;
    Integer f10538m;
    Integer f10539n;

    /* compiled from: vungle */
    protected static abstract class C4222a<A extends jh<A, V, R>, V extends jg<A>, R extends wp> extends a<A, V, R> {
        protected abstract b m13484a();

        protected C4222a() {
        }

        protected V m13486a(A a, R r) {
            jg jgVar = (jg) super.a(a, r);
            if (jgVar != null) {
                m13481b(jgVar, r);
            }
            return jgVar;
        }

        public int m13482a(V v, wp wpVar) {
            return m13481b(v, wpVar).f_();
        }

        private V m13481b(V v, wp wpVar) {
            v.f10532g = wpVar.m14055w();
            v.f10536k = wpVar.m14052t();
            v.f10537l = wpVar.m14053u();
            v.f10538m = wpVar.m14054v();
            v.f10539n = wpVar.m14057y();
            C4263a r = wpVar.m14050r();
            if (r != null) {
                v.f10530e = r.m14078c();
                v.f10531f = r.m14082h();
                v.f10533h = r.m14079d();
                v.f10534i = r.m14080f();
                v.f10535j = r.m14081g();
            }
            return v;
        }

        protected V m13487a(String str, boolean z) throws SQLException {
            return (jg) a(str, m13484a(), z);
        }

        protected V m13485a(V v, Cursor cursor, boolean z) {
            super.a(v, cursor, z);
            v.f10530e = ce.b(cursor, "cta_clickable_percent");
            v.f10531f = ce.c(cursor, "enable_cta_delay_seconds");
            v.f10532g = ce.c(cursor, "height");
            v.f10533h = ce.a(cursor, "is_cta_enabled");
            v.f10534i = ce.a(cursor, "is_cta_shown_on_touch");
            v.f10535j = ce.c(cursor, "show_cta_delay_seconds");
            v.f10536k = ce.c(cursor, "show_close_delay_incentivized_seconds");
            v.f10537l = ce.c(cursor, "show_close_delay_interstitial_seconds");
            v.f10538m = ce.c(cursor, "show_countdown_delay_seconds");
            v.f10539n = ce.c(cursor, "width");
            return v;
        }
    }

    public abstract Uri m13514x();

    protected jg() {
    }

    public Float m13503D() {
        return this.f10530e;
    }

    public Integer m13504E() {
        return this.f10531f;
    }

    public Boolean m13505F() {
        return this.f10533h;
    }

    public Boolean m13506G() {
        return this.f10534i;
    }

    public Integer m13507H() {
        return this.f10535j;
    }

    public Integer m13508I() {
        return this.f10536k;
    }

    public Integer m13509J() {
        return this.f10537l;
    }

    public boolean m13510K() {
        return (this.f10532g == null || this.f10539n == null || this.f10539n.intValue() <= this.f10532g.intValue()) ? false : true;
    }

    public boolean m13511L() {
        return (this.f10532g == null || this.f10539n == null || this.f10532g.intValue() <= this.f10539n.intValue()) ? false : true;
    }

    protected ContentValues mo6942a(boolean z) {
        ContentValues a = super.mo6942a(z);
        a.put("cta_clickable_percent", this.f10530e);
        a.put("enable_cta_delay_seconds", this.f10531f);
        a.put("height", this.f10532g);
        a.put("is_cta_enabled", this.f10533h);
        a.put("is_cta_shown_on_touch", this.f10534i);
        a.put("show_cta_delay_seconds", this.f10535j);
        a.put("show_close_delay_incentivized_seconds", this.f10536k);
        a.put("show_close_delay_interstitial_seconds", this.f10537l);
        a.put("show_countdown_delay_seconds", this.f10538m);
        a.put("width", this.f10539n);
        return a;
    }

    protected StringBuilder mo6943p() {
        StringBuilder p = super.mo6943p();
        a(p, "cta_clickable_percent", this.f10530e);
        a(p, "enable_cta_delay_seconds", this.f10531f);
        a(p, "height", this.f10532g);
        a(p, "is_cta_enabled", this.f10533h);
        a(p, "is_cta_shown_on_touch", this.f10534i);
        a(p, "show_cta_delay_seconds", this.f10535j);
        a(p, "show_close_delay_incentivized_seconds", this.f10536k);
        a(p, "show_close_delay_interstitial_seconds", this.f10537l);
        a(p, "show_countdown_delay_seconds", this.f10538m);
        a(p, "width", this.f10539n);
        return p;
    }
}
