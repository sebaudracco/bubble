package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.yandex.metrica.C4275a;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.GoogleAdvertisingIdGetter.C4301b;
import com.yandex.metrica.impl.bb.C4353a;
import com.yandex.metrica.impl.bg.C4361a;
import com.yandex.metrica.impl.interact.DeviceInfo;
import com.yandex.metrica.impl.ob.C4484g;
import com.yandex.metrica.impl.ob.C4488m;
import com.yandex.metrica.impl.ob.C4489n;
import com.yandex.metrica.impl.ob.C4491p;
import com.yandex.metrica.impl.ob.C4503t;
import com.yandex.metrica.impl.ob.cd;
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.utils.C4528i;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class ba {
    private List<String> f11730A;
    private String f11731B;
    private String f11732C;
    private String f11733D;
    private String f11734E;
    private boolean f11735F;
    private boolean f11736G;
    private boolean f11737H;
    private boolean f11738I;
    private String f11739J;
    private String f11740K;
    private String f11741L;
    private String f11742a = Build.MANUFACTURER;
    private String f11743b = Build.MODEL;
    private String f11744c = VERSION.RELEASE;
    private int f11745d = VERSION.SDK_INT;
    private String f11746e = "273";
    private String f11747f = bc.m14873a();
    private String f11748g = "7854";
    private String f11749h;
    private String f11750i;
    private String f11751j;
    private String f11752k;
    private String f11753l;
    private String f11754m;
    private String f11755n;
    private String f11756o;
    private String f11757p;
    private int f11758q;
    private int f11759r;
    private int f11760s;
    private float f11761t;
    private String f11762u;
    private String f11763v;
    private String f11764w;
    private String f11765x;
    private String f11766y;
    private List<String> f11767z;

    public ba() {
        this.f11749h = TextUtils.isEmpty("") ? HeaderConstants.PUBLIC : "public_";
        this.f11750i = "android";
        this.f11751j = "2";
        this.f11762u = C4275a.PHONE.name().toLowerCase(Locale.US);
        this.f11734E = SchemaSymbols.ATTVAL_FALSE_0;
    }

    public String m14807a() {
        return this.f11756o;
    }

    public synchronized String m14819b() {
        return m14788a(this.f11752k, "");
    }

    public synchronized void m14815a(String str) {
        if (!bi.m14957a(str)) {
            this.f11752k = str;
        }
    }

    public synchronized void m14822b(String str) {
        if (!bi.m14957a(str)) {
            this.f11753l = str;
        }
    }

    public synchronized String m14824c() {
        return m14788a(this.f11753l, "");
    }

    public String m14829d() {
        return m14788a(this.f11757p, "");
    }

    public void m14826c(String str) {
        this.f11757p = str;
    }

    public synchronized void m14830d(String str) {
        this.f11754m = str;
    }

    public synchronized String m14832e() {
        return this.f11754m;
    }

    public String m14835f() {
        return this.f11751j;
    }

    public String m14837g() {
        return this.f11747f;
    }

    public void m14834e(String str) {
        this.f11746e = str;
    }

    public String m14839h() {
        return this.f11746e;
    }

    public void m14836f(String str) {
        this.f11764w = str;
    }

    public String m14841i() {
        return this.f11764w;
    }

    public int m14843j() {
        return C4528i.m16275a(this.f11764w, 0);
    }

    public String m14845k() {
        return this.f11748g;
    }

    public void m14838g(String str) {
        this.f11748g = str;
    }

    public String m14847l() {
        return this.f11749h;
    }

    public void m14840h(String str) {
        this.f11749h = str;
    }

    public String m14849m() {
        return this.f11750i;
    }

    public String m14851n() {
        return m14788a(this.f11755n, "");
    }

    public String m14853o() {
        return m14788a(this.f11742a, "");
    }

    public String m14855p() {
        return m14788a(this.f11743b, "");
    }

    public String m14857q() {
        return m14788a(this.f11744c, "");
    }

    public int m14859r() {
        return this.f11745d;
    }

    public void m14842i(String str) {
        this.f11744c = str;
    }

    public void m14810a(int i) {
        this.f11745d = i;
    }

    public int m14861s() {
        return this.f11758q;
    }

    public int m14863t() {
        return this.f11759r;
    }

    public int m14864u() {
        return this.f11760s;
    }

    public float m14865v() {
        return this.f11761t;
    }

    public String m14866w() {
        return m14788a(this.f11763v, "");
    }

    public void m14844j(String str) {
        this.f11763v = str;
    }

    public String m14867x() {
        return m14788a(this.f11765x, "");
    }

    public void m14848l(String str) {
        this.f11739J = str;
    }

    public String m14868y() {
        return m14788a(this.f11739J, "");
    }

    public String m14869z() {
        return m14788a(this.f11766y, "");
    }

    public void m14816a(List<String> list) {
        this.f11767z = list;
    }

    public void m14852n(String str) {
        this.f11731B = str;
    }

    public void m14854o(String str) {
        this.f11732C = str;
    }

    public String m14793A() {
        return m14788a(this.f11732C, "");
    }

    public String m14794B() {
        return m14788a(this.f11733D, "");
    }

    public void m14856p(String str) {
        this.f11733D = str;
    }

    public String m14795C() {
        return m14788a(this.f11731B, "");
    }

    public List<String> m14796D() {
        List<String> arrayList = new ArrayList();
        if (!bk.m14987a(this.f11767z)) {
            arrayList.addAll(this.f11767z);
        }
        if (!bk.m14987a(this.f11730A)) {
            arrayList.addAll(this.f11730A);
        }
        arrayList.add("https://startup.mobile.yandex.net/");
        return arrayList;
    }

    public List<String> m14797E() {
        return this.f11767z;
    }

    public void m14858q(String str) {
        this.f11734E = str;
    }

    public String m14798F() {
        return m14788a(this.f11734E, SchemaSymbols.ATTVAL_FALSE_0);
    }

    public String m14799G() {
        return m14788a(this.f11762u, C4275a.PHONE.name().toLowerCase(Locale.US));
    }

    public boolean m14800H() {
        return this.f11735F;
    }

    public void m14817a(boolean z) {
        this.f11735F = z;
    }

    public boolean m14801I() {
        return this.f11736G;
    }

    public boolean m14802J() {
        return this.f11737H;
    }

    public boolean m14803K() {
        return this.f11738I;
    }

    public void m14823b(boolean z) {
        this.f11736G = z;
    }

    public void m14827c(boolean z) {
        this.f11737H = z;
    }

    public void m14831d(boolean z) {
        this.f11738I = z;
    }

    public String m14804L() {
        return m14788a(this.f11740K, "https://certificate.mobile.yandex.net/api/v1/pins");
    }

    public void m14860r(String str) {
        this.f11740K = str;
    }

    public synchronized boolean m14805M() {
        boolean z = true;
        synchronized (this) {
            if (bi.m14959a(m14819b(), m14824c(), m14795C())) {
                z = false;
            }
        }
        return z;
    }

    public synchronized boolean m14818a(long j) {
        boolean z = false;
        synchronized (this) {
            if (m14805M()) {
                long currentTimeMillis = (System.currentTimeMillis() / 1000) - j;
                if (currentTimeMillis <= 86400 && currentTimeMillis >= 0) {
                    z = true;
                }
            }
        }
        return z;
    }

    public void m14813a(C4503t c4503t) {
        Context m = c4503t.mo7114m();
        String b = c4503t.mo7113l().m16105b();
        CounterConfiguration j = c4503t.mo7112j();
        DeviceInfo instance = DeviceInfo.getInstance(m);
        cd E = c4503t.m16136E();
        this.f11756o = bk.m14972a(m, j, b);
        this.f11762u = m14809a(m, j);
        List a = be.m14889a(m, be.m14888a(m).setPackage(b));
        C4353a c4353a = null;
        if (!a.isEmpty()) {
            c4353a = bb.m14870a(be.m14886a(((ResolveInfo) a.get(0)).serviceInfo));
        }
        if (c4353a == null) {
            c4353a = bb.f11770a;
            Map hashMap = new HashMap();
            hashMap.put("package", b);
            YandexMetrica.getReporter(m, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("invalid_sdk_version", hashMap);
        }
        this.f11764w = c4353a.f11768a;
        m14789a(instance);
        m14814a(c4503t, E);
        m14791b(c4503t, E);
        m14790b(E);
        String o = j.m14282o();
        if (bi.m14957a(o)) {
            o = m14867x();
            if (bi.m14957a(o)) {
                o = bk.m14973a(m, b);
            }
        }
        m14846k(o);
        o = j.m14283p();
        if (bi.m14957a(o)) {
            o = m14869z();
            if (bi.m14957a(o)) {
                o = String.valueOf(bk.m14990b(m, b));
            }
        }
        m14850m(o);
        CharSequence packageName = c4503t.mo7114m().getPackageName();
        try {
            this.f11741L = m14787a(m14828d(c4503t));
        } catch (NameNotFoundException e) {
            if (TextUtils.equals(packageName, c4503t.mo7113l().m16105b())) {
                this.f11741L = m14787a(c4503t.mo7114m().getApplicationInfo());
            } else {
                this.f11741L = SchemaSymbols.ATTVAL_FALSE_0;
            }
        }
        m14833e(c4503t);
    }

    void m14821b(C4503t c4503t) {
        cd E = c4503t.m16136E();
        m14791b(c4503t, E);
        m14814a(c4503t, E);
        m14833e(c4503t);
    }

    private void m14790b(cd cdVar) {
        this.f11739J = cdVar.m15516d(null);
    }

    public void m14825c(C4503t c4503t) {
        m14789a(DeviceInfo.getInstance(c4503t.mo7114m()));
        m14790b(c4503t.m16136E());
    }

    String m14809a(Context context, CounterConfiguration counterConfiguration) {
        C4275a e = counterConfiguration.m14264e();
        return e == null ? m14820b(context) : e.m14313a();
    }

    String m14820b(Context context) {
        return DeviceInfo.getInstance(context).deviceType;
    }

    private synchronized void m14791b(C4503t c4503t, cd cdVar) {
        String c = m14824c();
        if (bi.m14957a(c)) {
            c = c4503t.mo7112j().m14273h();
            if (bi.m14957a(c)) {
                c = ci.m15569a().m15575a(c4503t.mo7114m());
            }
        }
        C4484g.m16084a().m16092b(new C4489n(c));
        m14822b(c);
        c = c4503t.mo7112j().m14270g();
        if (bi.m14957a(c)) {
            c = m14819b();
            if (bi.m14957a(c)) {
                c = cdVar.m15510b("");
            }
        }
        C4484g.m16084a().m16092b(new C4491p(c, c4503t.mo7113l().m16105b()));
        m14815a(c);
    }

    private void m14789a(DeviceInfo deviceInfo) {
        this.f11755n = deviceInfo.platformDeviceId;
        C4484g.m16084a().m16092b(new C4488m(this.f11755n));
        this.f11760s = deviceInfo.screenDpi;
        this.f11761t = deviceInfo.scaleFactor;
        int i = deviceInfo.screenWidth;
        int i2 = deviceInfo.screenHeight;
        this.f11758q = Math.max(i, i2);
        this.f11759r = Math.min(i, i2);
        this.f11763v = deviceInfo.getLocale();
        this.f11734E = deviceInfo.deviceRootStatus;
    }

    ApplicationInfo m14828d(C4503t c4503t) throws NameNotFoundException {
        return c4503t.mo7114m().getPackageManager().getApplicationInfo(c4503t.mo7113l().m16105b(), 0);
    }

    private static String m14787a(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 2) != 0 ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0;
    }

    public synchronized void m14811a(C4361a c4361a) {
        m14815a(c4361a.m14921i());
        m14822b(c4361a.m14919h());
        m14854o(c4361a.m14909d());
        m14816a(c4361a.m14906c());
        m14852n(c4361a.m14912e());
        m14856p(c4361a.m14915f());
        m14860r(c4361a.m14917g());
        m14848l(c4361a.m14922j());
        m14817a(c4361a.m14902a());
        m14823b(c4361a.m14905b());
        m14827c(c4361a.m14928p());
        m14831d(c4361a.m14929q());
    }

    public synchronized void m14833e(C4503t c4503t) {
        CounterConfiguration j = c4503t.mo7112j();
        m14815a(j.m14270g());
        m14830d(j.m14276i());
        m14846k(j.m14282o());
        m14850m(j.m14283p());
        m14826c(j.m14237E());
        m14792f(c4503t);
    }

    private void m14792f(C4503t c4503t) {
        CounterConfiguration j = c4503t.mo7112j();
        if (c4503t.mo7113l().m16107d() || c4503t.mo7113l().m16106c()) {
            cd E = c4503t.m16136E();
            Object n = j.m14281n();
            if (bk.m14987a((Collection) n) && !bk.m14987a(this.f11730A)) {
                E.m15508b(null).m15415h();
                this.f11730A = null;
            }
            if (!bk.m14987a((Collection) n) && !bk.m14986a(n, this.f11730A)) {
                this.f11730A = n;
                E.m15508b(this.f11730A).m15415h();
            }
        }
    }

    public String m14806N() {
        return this.f11741L;
    }

    public void m14862s(String str) {
        this.f11741L = str;
    }

    public String m14808a(Context context) {
        return m14788a(C4301b.f11553a.m14439b(context), "");
    }

    public void m14846k(String str) {
        if (!bi.m14957a(str)) {
            this.f11765x = str;
        }
    }

    public void m14850m(String str) {
        if (!bi.m14957a(str)) {
            this.f11766y = str;
        }
    }

    private static String m14788a(String str, String str2) {
        return !bi.m14957a(str) ? str : str2;
    }

    void m14814a(C4503t c4503t, cd cdVar) {
        this.f11732C = cdVar.m15521f("");
        this.f11733D = cdVar.m15523g("");
        cdVar.m15514c("https://startup.mobile.yandex.net/");
        this.f11767z = cdVar.m15506a();
        this.f11730A = cdVar.m15511b();
        this.f11731B = cdVar.m15519e("");
        m14792f(c4503t);
        m14812a(cdVar);
    }

    void m14812a(cd cdVar) {
        m14817a(cdVar.m15520e());
        m14823b(cdVar.m15526i());
        m14827c(cdVar.m15528j());
        m14831d(cdVar.m15530k());
    }
}
