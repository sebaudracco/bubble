package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.utils.C4525g;
import java.util.List;

public class cd extends cb {
    static final dk f12040a = new dk("PREF_KEY_UID_");
    static final dk f12041b = new dk("PREF_KEY_DEVICE_ID_");
    private static final dk f12042c = new dk("PREF_KEY_HOST_URL_");
    private static final dk f12043d = new dk("PREF_KEY_HOST_URLS_FROM_STARTUP");
    private static final dk f12044e = new dk("PREF_KEY_HOST_URLS_FROM_CLIENT");
    private static final dk f12045f = new dk("PREF_KEY_REPORT_URL_");
    private static final dk f12046g = new dk("PREF_KEY_GET_AD_URL");
    private static final dk f12047h = new dk("PREF_KEY_REPORT_AD_URL");
    private static final dk f12048i = new dk("PREF_KEY_STARTUP_OBTAIN_TIME_");
    private static final dk f12049j = new dk("PREF_KEY_STARTUP_ENCODED_CLIDS_");
    private static final dk f12050k = new dk("PREF_KEY_DISTRIBUTION_REFERRER_");
    private static final dk f12051l = new dk("STARTUP_CLIDS_MATCH_WITH_APP_CLIDS_");
    private static final dk f12052m = new dk("PREF_KEY_PINNING_UPDATE_URL");
    private static final dk f12053n = new dk("PREF_KEY_EASY_COLLECTING_ENABLED_");
    private static final dk f12054o = new dk("PREF_KEY_COLLECTING_PACKAGE_INFO_ENABLED_");
    private static final dk f12055p = new dk("PREF_KEY_PERMISSIONS_COLLECTING_ENABLED_");
    private static final dk f12056q = new dk("PREF_KEY_FEATURES_COLLECTING_ENABLED_");
    private static final dk f12057r = new dk("SOCKET_CONFIG_");
    private static final dk f12058s = new dk("LAST_STARTUP_REQUEST_CLIDS");
    private static final dk f12059t = new dk("LAST_STARTUP_CLIDS_SAVE_TIME");
    private dk f12060A;
    private dk f12061B;
    private dk f12062C;
    private dk f12063D;
    private dk f12064E;
    private dk f12065F;
    private dk f12066G;
    private dk f12067H;
    private dk f12068I;
    private dk f12069J;
    private dk f12070K;
    private dk f12071L;
    private dk f12072M;
    private dk f12073u;
    private dk f12074v;
    private dk f12075w;
    private dk f12076x;
    private dk f12077y;
    private dk f12078z;

    public cd(bq bqVar, String str) {
        super(bqVar, str);
    }

    protected void mo7060f() {
        super.mo7060f();
        this.f12073u = new dk(f12041b.m15728a());
        this.f12074v = m15416q(f12040a.m15728a());
        this.f12075w = m15416q(f12042c.m15728a());
        this.f12076x = m15416q(f12043d.m15728a());
        this.f12077y = m15416q(f12044e.m15728a());
        this.f12078z = m15416q(f12045f.m15728a());
        this.f12060A = m15416q(f12046g.m15728a());
        this.f12061B = m15416q(f12047h.m15728a());
        this.f12062C = m15416q(f12048i.m15728a());
        this.f12063D = m15416q(f12049j.m15728a());
        this.f12064E = m15416q(f12050k.m15728a());
        this.f12065F = m15416q(f12051l.m15728a());
        this.f12066G = m15416q(f12053n.m15728a());
        this.f12067H = m15416q(f12054o.m15728a());
        this.f12068I = m15416q(f12055p.m15728a());
        this.f12069J = m15416q(f12056q.m15728a());
        this.f12071L = m15416q(f12058s.m15728a());
        this.f12072M = m15416q(f12059t.m15728a());
        this.f12070K = m15416q(f12057r.m15728a());
    }

    public long m15502a(long j) {
        return m15410b(this.f12062C.m15730b(), j);
    }

    public String m15505a(String str) {
        return m15411b(this.f12073u.m15730b(), str);
    }

    public String m15510b(String str) {
        return m15411b(this.f12074v.m15730b(), str);
    }

    public String m15514c(String str) {
        return m15411b(this.f12075w.m15730b(), str);
    }

    public List<String> m15506a() {
        return C4525g.m16274b(m15411b(this.f12076x.m15730b(), null));
    }

    public List<String> m15511b() {
        return C4525g.m16274b(m15411b(this.f12077y.m15730b(), null));
    }

    public String m15516d(String str) {
        return m15411b(this.f12063D.m15730b(), str);
    }

    public String m15519e(String str) {
        return m15411b(this.f12078z.m15730b(), str);
    }

    public String m15521f(String str) {
        return m15411b(this.f12060A.m15730b(), str);
    }

    public String m15523g(String str) {
        return m15411b(this.f12061B.m15730b(), str);
    }

    public String m15513c() {
        return m15411b(this.f12064E.m15730b(), null);
    }

    public boolean m15517d() {
        return m15412b(this.f12065F.m15730b(), true);
    }

    public boolean m15520e() {
        return m15412b(this.f12066G.m15730b(), false);
    }

    public boolean m15526i() {
        return m15412b(this.f12067H.m15730b(), false);
    }

    public boolean m15528j() {
        return m15412b(this.f12068I.m15730b(), false);
    }

    public boolean m15530k() {
        return m15412b(this.f12069J.m15730b(), false);
    }

    public String m15532l() {
        return m15411b(this.f12070K.m15730b(), null);
    }

    public String m15524h(String str) {
        return m15411b(f12052m.m15730b(), str);
    }

    public cd m15525i(String str) {
        return (cd) m15407a(f12052m.m15730b(), str);
    }

    public cd m15527j(String str) {
        return (cd) m15407a(this.f12074v.m15730b(), str);
    }

    public cd m15529k(String str) {
        return (cd) m15407a(this.f12073u.m15730b(), str);
    }

    public cd m15531l(String str) {
        return (cd) m15407a(this.f12078z.m15730b(), str);
    }

    public cd m15533m(String str) {
        return (cd) m15407a(this.f12061B.m15730b(), str);
    }

    public cd m15536n(String str) {
        return (cd) m15407a(this.f12060A.m15730b(), str);
    }

    public cd m15537o(String str) {
        return (cd) m15407a(this.f12075w.m15730b(), str);
    }

    public cd m15503a(List<String> list) {
        return (cd) m15407a(this.f12076x.m15730b(), C4525g.m16270a((List) list));
    }

    public cd m15508b(List<String> list) {
        return (cd) m15407a(this.f12077y.m15730b(), C4525g.m16270a((List) list));
    }

    public cd m15504a(boolean z) {
        return (cd) m15408a(this.f12066G.m15730b(), z);
    }

    public cd m15509b(boolean z) {
        return (cd) m15408a(this.f12067H.m15730b(), z);
    }

    public cd m15512c(boolean z) {
        return (cd) m15408a(this.f12068I.m15730b(), z);
    }

    public cd m15515d(boolean z) {
        return (cd) m15408a(this.f12069J.m15730b(), z);
    }

    public cd m15507b(long j) {
        return (cd) m15406a(this.f12062C.m15730b(), j);
    }

    public cd m15538p(String str) {
        return (cd) m15407a(this.f12063D.m15730b(), str);
    }

    public cd m15539s(String str) {
        return (cd) m15407a(this.f12064E.m15730b(), str);
    }

    public cd m15518e(boolean z) {
        return (cd) m15408a(this.f12065F.m15730b(), z);
    }

    public cd m15540t(String str) {
        return (cd) m15407a(this.f12070K.m15730b(), str);
    }

    public String m15534m() {
        return m15411b(this.f12071L.m15730b(), null);
    }

    public long m15535n() {
        return m15410b(this.f12072M.m15730b(), -1);
    }

    public cd m15541u(String str) {
        return (cd) m15407a(this.f12071L.m15730b(), str).m15406a(this.f12072M.m15730b(), System.currentTimeMillis());
    }
}
