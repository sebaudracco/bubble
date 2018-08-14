package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.coremedia.iso.boxes.UserBox;
import com.yandex.metrica.impl.bg.C4361a;
import com.yandex.metrica.impl.bg.C4361a.C4360a;
import com.yandex.metrica.impl.ob.C4484g;
import com.yandex.metrica.impl.ob.C4490o;
import com.yandex.metrica.impl.ob.C4503t;
import com.yandex.metrica.impl.ob.cd;
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.ob.co;
import com.yandex.metrica.impl.ob.cq;
import com.yandex.metrica.impl.ob.cu;
import com.yandex.metrica.impl.ob.du;
import com.yandex.metrica.impl.utils.C4531k;
import com.yandex.metrica.impl.utils.C4532l;
import java.util.List;
import java.util.Map;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONException;

class bh extends ak {
    private ba f11800a;
    private Context f11801b;
    private C4503t f11802c;
    private cd f11803m;
    private boolean f11804n = false;
    private du f11805o;
    private List<String> f11806p;

    public bh(C4503t c4503t) {
        this.f11802c = c4503t;
        this.f11801b = c4503t.mo7114m();
        this.f11800a = c4503t.mo7111h();
        this.f11803m = c4503t.m16136E();
        this.f11806p = this.f11800a.m14796D();
    }

    public boolean mo6996b() {
        m14945a(false);
        this.f11800a.m14821b(this.f11802c);
        if (m14955s()) {
            return true;
        }
        return false;
    }

    cq mo6998d() {
        return new cu(this.f11803m.m15524h(null)).m15617a(m14580h());
    }

    boolean m14955s() {
        boolean z = !this.f11800a.m14818a(this.f11803m.m15502a(0));
        Object a = C4532l.m16291a(this.f11802c.mo7112j().m14288u());
        if (!(z || TextUtils.isEmpty(a))) {
            if (a.equals(this.f11803m.m15534m())) {
                z = (System.currentTimeMillis() - this.f11803m.m15535n()) / 1000 > 86400;
            } else {
                z = true;
            }
        }
        if (z) {
            return z;
        }
        CharSequence d = ci.m15569a().m15580d();
        if (TextUtils.isEmpty(d)) {
            if (TextUtils.isEmpty(m14939a(this.f11800a))) {
                return false;
            }
            return true;
        } else if (TextUtils.equals(m14939a(this.f11800a), d)) {
            return false;
        } else {
            return true;
        }
    }

    private static String m14939a(ba baVar) {
        CharSequence c = baVar.m14824c();
        String e = baVar.m14832e();
        if (TextUtils.isEmpty(c)) {
            return TextUtils.isEmpty(e) ? "" : e;
        } else {
            return c;
        }
    }

    void m14943a(Builder builder) {
        builder.path("analytics/startup");
        builder.appendQueryParameter("deviceid", m14939a(this.f11800a));
        builder.appendQueryParameter("app_platform", this.f11800a.m14849m());
        builder.appendQueryParameter("protocol_version", this.f11800a.m14835f());
        builder.appendQueryParameter("analytics_sdk_version", this.f11800a.m14839h());
        builder.appendQueryParameter("analytics_sdk_version_name", this.f11800a.m14837g());
        builder.appendQueryParameter("model", this.f11800a.m14855p());
        builder.appendQueryParameter("manufacturer", this.f11800a.m14853o());
        builder.appendQueryParameter("os_version", this.f11800a.m14857q());
        builder.appendQueryParameter("screen_width", String.valueOf(this.f11800a.m14861s()));
        builder.appendQueryParameter("screen_height", String.valueOf(this.f11800a.m14863t()));
        builder.appendQueryParameter("screen_dpi", String.valueOf(this.f11800a.m14864u()));
        builder.appendQueryParameter("scalefactor", String.valueOf(this.f11800a.m14865v()));
        builder.appendQueryParameter(Constants.LOCALE_PROPERTY, this.f11800a.m14866w());
        builder.appendQueryParameter("device_type", this.f11800a.m14799G());
        builder.appendQueryParameter("query_hosts", "2");
        builder.appendQueryParameter(C1404b.f2119W, bi.m14962b("easy_collecting", "package_info", "socket", "permissions_collecting", "features_collecting"));
        builder.appendQueryParameter("browsers", SchemaSymbols.ATTVAL_TRUE_1);
        builder.appendQueryParameter("socket", SchemaSymbols.ATTVAL_TRUE_1);
        builder.appendQueryParameter("app_id", this.f11802c.mo7113l().m16105b());
        builder.appendQueryParameter("app_debuggable", this.f11800a.m14806N());
        Map u = this.f11802c.mo7112j().m14288u();
        Object v = this.f11802c.mo7112j().m14289v();
        if (TextUtils.isEmpty(v)) {
            v = this.f11803m.m15513c();
        }
        if (!bk.m14988a(u)) {
            builder.appendQueryParameter("distribution_customization", SchemaSymbols.ATTVAL_TRUE_1);
            m14940a(builder, "clids_set", C4532l.m16291a(u));
            if (!TextUtils.isEmpty(v)) {
                builder.appendQueryParameter("install_referrer", v);
            }
        }
        m14940a(builder, UserBox.TYPE, this.f11800a.m14819b());
    }

    private static void m14940a(Builder builder, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            builder.appendQueryParameter(str, str2);
        }
    }

    public boolean mo6997c() {
        this.k = false;
        if (m14956t()) {
            this.k = true;
        } else if (200 == this.h) {
            Map u = this.f11802c.mo7112j().m14288u();
            C4361a a = bg.m14930a(this.i);
            if (C4360a.OK == a.m14923k()) {
                this.f11803m.m15541u(this.f11800a.m14868y());
                this.f11800a.m14811a(a);
                Long a2 = bg.m14931a(m14584l());
                if (a2 != null) {
                    C4531k.m16287a().m16288a(a2.longValue());
                }
                this.f11800a.m14822b(ci.m15569a().m15579c(this.f11801b, this.f11800a.m14824c()));
                String str = "";
                if (a.m14927o() == null) {
                    C4484g.m16084a().m16089a(C4490o.class);
                } else {
                    try {
                        str = a.m14927o().m15790d();
                        C4484g.m16084a().m16092b(new C4490o(a.m14927o()));
                    } catch (JSONException e) {
                    }
                }
                m14944a(this.f11800a, str);
                this.f11802c.m16147a(C4532l.m16292a(this.f11800a.m14868y()).equals(u));
                C4382j.m15103a(this.f11802c.m16167k(), this.f11800a, a);
                this.k = true;
            } else {
                this.f11805o = du.PARSE;
            }
        }
        return this.k;
    }

    public void mo7026g() {
        this.f11805o = du.NETWORK;
    }

    public void mo7000f() {
        if (!this.k) {
            if (this.f11805o == null) {
                this.f11805o = du.f12264a;
            }
            C4382j.m15104a(this.f11802c.m16167k(), this.f11805o);
        }
    }

    synchronized void m14944a(ba baVar, String str) {
        if (!m14956t()) {
            this.f11803m.m15527j(baVar.m14819b()).m15503a(baVar.m14797E()).m15531l(baVar.m14795C()).m15533m(baVar.m14794B()).m15536n(baVar.m14793A()).m15525i(baVar.m14804L()).m15504a(baVar.m14800H()).m15509b(baVar.m14801I()).m15512c(baVar.m14802J()).m15515d(baVar.m14803K()).m15540t(str);
            Object y = baVar.m14868y();
            if (!TextUtils.isEmpty(y)) {
                this.f11803m.m15538p(y);
            }
            this.f11803m.m15415h();
            m14942a(System.currentTimeMillis() / 1000);
            co.m15605a().m15608a(this.f11801b, this.f11800a.m14819b(), baVar.m14804L());
            if (!bi.m14957a(baVar.m14824c())) {
                Intent intent = new Intent("com.yandex.metrica.intent.action.SYNC");
                intent.putExtra("CAUSE", "CAUSE_DEVICE_ID");
                intent.putExtra("SYNC_TO_PKG", this.f11802c.mo7113l().m16105b());
                intent.putExtra("SYNC_DATA", baVar.m14824c());
                intent.putExtra("SYNC_DATA_2", baVar.m14819b());
                this.f11801b.sendBroadcast(intent);
            }
        }
    }

    synchronized void m14942a(long j) {
        this.f11803m.m15507b(j).m15415h();
    }

    synchronized void m14945a(boolean z) {
        this.f11804n = z;
    }

    synchronized boolean m14956t() {
        return this.f11804n;
    }

    public boolean mo7027n() {
        return true;
    }

    public boolean mo7001o() {
        return m14589q() + 1 < this.f11806p.size();
    }

    public long mo7002p() {
        return 0;
    }

    public void mo6999e() {
        super.mo6999e();
        Builder buildUpon = Uri.parse((String) this.f11806p.get(m14589q())).buildUpon();
        m14943a(buildUpon);
        m14569a(buildUpon.build().toString());
    }

    public String mo6995a() {
        return "Startup task for component: " + this.f11802c.mo7113l().toString();
    }
}
