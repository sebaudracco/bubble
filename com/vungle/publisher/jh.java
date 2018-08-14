package com.vungle.publisher;

import android.content.ContentValues;
import android.database.SQLException;
import java.util.List;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class jh<A extends jh<A, V, R>, V extends jg<A>, R extends wp> extends cn {
    protected String f2869A;
    protected String f2870B;
    protected V f2871C;
    protected je<?, A, R, ji, ?, ?> f2872D;
    protected boolean f2873E;
    @Inject
    qg f2874F;

    protected abstract a<A, V, R> mo2983x();

    protected /* synthetic */ cn$a mo2979a() {
        return mo2983x();
    }

    public /* synthetic */ Object d_() {
        return mo2982w();
    }

    public String m3845y() {
        return this.f2869A;
    }

    public String m3846z() {
        return this.f2870B;
    }

    public List<String> m3841a(jf jfVar) {
        return this.f2872D.a(jfVar);
    }

    public V m3837D() {
        return mo2983x().a(this, false);
    }

    public void m3838E() {
        if (this.f2872D != null) {
            this.f2872D.c();
        }
    }

    public String mo2982w() throws SQLException {
        String str = (String) super.d_();
        m3838E();
        if (this.f2871C != null) {
            this.f2871C.d_();
        }
        return str;
    }

    public int f_() {
        int f_ = super.f_();
        if (f_ == 1 && this.f2871C != null) {
            this.f2871C.f_();
        }
        return f_;
    }

    protected ContentValues m3839a(boolean z) {
        ContentValues a = super.a(z);
        a.put("call_to_action_final_url", this.f2869A);
        a.put("call_to_action_url", this.f2870B);
        return a;
    }

    protected StringBuilder m3842p() {
        StringBuilder p = super.p();
        a(p, "call_to_action_final_url", this.f2869A);
        a(p, "call_to_action_url", this.f2870B);
        this.f2872D.a(p);
        return p;
    }
}
