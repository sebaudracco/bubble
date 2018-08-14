package com.yandex.metrica.impl;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.C4276b;
import com.yandex.metrica.impl.C4511p.C4510a;
import com.yandex.metrica.impl.ob.dw;
import com.yandex.metrica.impl.utils.C4523f.C4522a;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract class C4306b implements C4276b {
    public static final Collection<Integer> f11571a;
    protected final aw f11572b;
    protected final ay f11573c;
    private C4540w f11574d;

    static {
        Collection hashSet = new HashSet();
        hashSet.add(Integer.valueOf(1));
        hashSet.add(Integer.valueOf(2));
        hashSet.add(Integer.valueOf(3));
        hashSet.add(Integer.valueOf(4));
        hashSet.add(Integer.valueOf(5));
        hashSet.add(Integer.valueOf(6));
        hashSet.add(Integer.valueOf(7));
        hashSet.add(Integer.valueOf(8));
        hashSet.add(Integer.valueOf(11));
        hashSet.add(Integer.valueOf(12));
        hashSet.add(Integer.valueOf(13));
        hashSet.add(Integer.valueOf(16));
        hashSet.add(Integer.valueOf(17));
        hashSet.add(Integer.valueOf(18));
        hashSet.add(Integer.valueOf(19));
        f11571a = Collections.unmodifiableCollection(hashSet);
    }

    C4306b(Context context, String str, ay ayVar, aw awVar) {
        context.getApplicationContext();
        this.f11573c = ayVar;
        this.f11572b = awVar;
        this.f11572b.m14714b().m14248a(str);
        this.f11572b.m14714b().m14258c(context.getPackageName());
        this.f11572b.m14711a(C4522a.m16256d());
    }

    protected void m14455a() {
        this.f11573c.m14746a(this.f11572b);
    }

    void mo7124a(dw dwVar) {
        this.f11572b.m14715b(dwVar);
    }

    void mo7123a(C4382j c4382j) {
        this.f11572b.m14709a(c4382j);
    }

    void m14460a(C4540w c4540w) {
        this.f11574d = c4540w;
    }

    public void m14461a(String str) {
        this.f11572b.m14714b().m14271g(str);
    }

    public void mo7036a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.f11572b.m14712a(str, str2);
        }
    }

    public void m14463a(Map<String, String> map) {
        if (!bk.m14988a((Map) map)) {
            for (Entry entry : map.entrySet()) {
                mo7036a((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    public void m14467b(Map<String, String> map) {
        if (!bk.m14988a((Map) map)) {
            for (Entry entry : map.entrySet()) {
                mo7125b((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    public void mo7125b(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.f11573c.m14753a(str, str2, this.f11572b);
        }
    }

    public void m14464b() {
        this.f11573c.m14759b(this.f11572b);
    }

    public void onResumeSession() {
        m14465b(null);
    }

    void m14465b(String str) {
        this.f11573c.m14764d();
        this.f11574d.m16303b();
        this.f11573c.m14747a(C4511p.m16200b(str), this.f11572b);
        if (this.f11572b.m14718e()) {
            this.f11573c.m14747a(C4511p.m16208d(C4510a.EVENT_TYPE_PURGE_BUFFER), this.f11572b);
        }
    }

    public void onPauseSession() {
        m14468c(null);
    }

    void m14468c(String str) {
        if (!this.f11572b.mo7119a()) {
            this.f11573c.m14765e();
            this.f11574d.m16302a();
            this.f11573c.m14747a(C4511p.m16204c(str), this.f11572b);
            this.f11572b.m14717d();
        }
    }

    public void reportEvent(String eventName) {
        reportEvent(eventName, null);
    }

    public void reportEvent(String eventName, String jsonValue) {
        bk.m14981a((Object) eventName, "Event Name");
        m14454a(C4511p.m16196a(eventName, jsonValue));
    }

    public void reportEvent(String eventName, Map<String, Object> attributes) {
        bk.m14981a((Object) eventName, "Event Name");
        this.f11573c.m14744a(C4511p.m16195a(eventName), m14470d(), bk.m14988a((Map) attributes) ? null : new HashMap(attributes));
    }

    public void m14457a(int i, String str, String str2, Map<String, String> map) {
        if (!f11571a.contains(Integer.valueOf(i))) {
            m14454a(C4511p.m16191a(i, str, str2, map == null ? null : new HashMap(map)));
        }
    }

    public void reportError(String message, Throwable error) {
        bk.m14981a((Object) message, "Message");
        m14454a(C4511p.m16201b(message, bk.m14975a(null, error)));
    }

    public void setSessionTimeout(int sessionTimeOut) {
        this.f11572b.m14714b().m14257c(sessionTimeOut);
    }

    public void reportUnhandledException(Throwable exception) {
        bk.m14981a((Object) exception, "Exception");
        this.f11573c.m14754a(exception, this.f11572b);
    }

    public void m14471d(String str) {
        bk.m14981a((Object) str, "Native Crash");
        this.f11573c.m14752a(str, this.f11572b);
    }

    public void mo6971a(int i) {
        this.f11572b.m14714b().m14253b(i);
    }

    boolean m14469c() {
        boolean z = !m14472e();
        if (z) {
            this.f11573c.m14747a(C4511p.m16204c(C4510a.EVENT_TYPE_ALIVE.m16189b()), this.f11572b);
        }
        return z;
    }

    aw m14470d() {
        return this.f11572b;
    }

    private void m14454a(C4372h c4372h) {
        this.f11573c.m14747a(c4372h, this.f11572b);
    }

    public boolean m14472e() {
        return this.f11572b.mo7119a();
    }
}
