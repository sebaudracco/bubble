package com.yandex.metrica.impl.ob;

import android.os.Bundle;
import android.text.TextUtils;
import com.yandex.metrica.IIdentifierCallback;
import com.yandex.metrica.impl.bi;
import java.util.List;
import java.util.Map;

public class dx {
    private String f12280a = this.f12289j.m15430a(null);
    private String f12281b = this.f12289j.m15432b(null);
    private String f12282c = this.f12289j.m15436c(null);
    private String f12283d = this.f12289j.m15438d(null);
    private String f12284e = this.f12289j.m15452n(null);
    private List<String> f12285f = this.f12289j.m15433b();
    private long f12286g = this.f12289j.m15427a(0);
    private String f12287h = this.f12289j.m15442e(null);
    private String f12288i = this.f12289j.m15450l(null);
    private final bz f12289j;

    public enum C4455a {
        IDENTIFIERS,
        URLS,
        f12278c
    }

    public dx(bz bzVar, String str) {
        this.f12289j = bzVar;
        m15812b(str);
        m15816e();
    }

    private void m15812b(String str) {
        if (bi.m14957a(this.f12280a) && !bi.m14957a(str)) {
            this.f12280a = str;
        }
    }

    synchronized void m15825a(Map<String, String> map) {
        m15829b((Map) map);
        m15831c((Map) map);
    }

    synchronized boolean m15827a(C4455a c4455a) {
        boolean h;
        if (C4455a.f12278c == c4455a) {
            h = m15820h();
        } else if (C4455a.IDENTIFIERS == c4455a) {
            h = m15818f();
        } else if (C4455a.URLS == c4455a) {
            h = m15819g();
        } else {
            h = false;
        }
        return h;
    }

    synchronized void m15829b(Map<String, String> map) {
        if (!bi.m14957a(this.f12280a)) {
            map.put(IIdentifierCallback.YANDEX_MOBILE_METRICA_UUID, this.f12280a);
        }
        if (!bi.m14957a(this.f12281b)) {
            map.put(IIdentifierCallback.YANDEX_MOBILE_METRICA_DEVICE_ID, this.f12281b);
        }
    }

    synchronized void m15831c(Map<String, String> map) {
        if (!bi.m14957a(this.f12282c)) {
            map.put(IIdentifierCallback.YANDEX_MOBILE_METRICA_GET_AD_URL, this.f12282c);
        }
        if (!bi.m14957a(this.f12283d)) {
            map.put(IIdentifierCallback.YANDEX_MOBILE_METRICA_REPORT_AD_URL, this.f12283d);
        }
    }

    synchronized void m15822a(Bundle bundle) {
        m15811b(bundle);
        m15813c(bundle);
        m15810b(bundle.getLong("ServerTimeOffset"));
        String string = bundle.getString("Clids");
        if (!bi.m14957a(string)) {
            this.f12287h = string;
        }
        Object string2 = bundle.getString("CookieBrowsers");
        if (!TextUtils.isEmpty(string2)) {
            this.f12288i = string2;
        }
        m15816e();
    }

    private void m15816e() {
        this.f12289j.m15444f(this.f12280a).m15445g(this.f12281b).m15446h(this.f12282c).m15447i(this.f12283d).m15437d(this.f12286g).m15448j(this.f12287h).m15451m(this.f12288i).m15453o(this.f12284e).m15415h();
    }

    void m15821a(long j) {
        this.f12289j.m15441e(j).m15415h();
    }

    List<String> m15828b() {
        return this.f12285f;
    }

    void m15824a(List<String> list) {
        this.f12285f = list;
        this.f12289j.m15428a(this.f12285f);
    }

    private synchronized boolean m15818f() {
        boolean z = true;
        synchronized (this) {
            if (bi.m14959a(this.f12280a, this.f12281b)) {
                z = false;
            }
        }
        return z;
    }

    private synchronized boolean m15819g() {
        boolean z = true;
        synchronized (this) {
            if (bi.m14959a(this.f12282c)) {
                z = false;
            }
        }
        return z;
    }

    private synchronized boolean m15820h() {
        boolean z;
        z = m15818f() && m15819g();
        return z;
    }

    private synchronized void m15811b(Bundle bundle) {
        m15812b(bundle.getString("UuId"));
        String string = bundle.getString("DeviceId");
        if (!bi.m14957a(string)) {
            m15823a(string);
        }
    }

    private synchronized void m15813c(Bundle bundle) {
        String string = bundle.getString("AdUrlGet");
        if (!TextUtils.isEmpty(string)) {
            m15814c(string);
        }
        Object string2 = bundle.getString("AdUrlReport");
        if (!TextUtils.isEmpty(string2)) {
            m15815d(string2);
        }
        string2 = bundle.getString("BindIdUrl");
        if (!TextUtils.isEmpty(string2)) {
            m15817e(string2);
        }
    }

    synchronized void m15823a(String str) {
        this.f12281b = str;
    }

    private synchronized void m15814c(String str) {
        this.f12282c = str;
    }

    private synchronized void m15815d(String str) {
        this.f12283d = str;
    }

    private synchronized void m15817e(String str) {
        this.f12284e = str;
    }

    private synchronized void m15810b(long j) {
        this.f12286g = j;
    }

    String m15830c() {
        return this.f12280a;
    }

    String m15832d() {
        return this.f12281b;
    }

    boolean m15826a() {
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - this.f12289j.m15431b(0);
        return currentTimeMillis > 86400 || currentTimeMillis < 0;
    }
}
