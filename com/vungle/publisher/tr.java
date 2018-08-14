package com.vungle.publisher;

import android.os.Bundle;
import java.util.regex.Pattern;

/* compiled from: vungle */
public abstract class tr {
    private static final Pattern f11088a = Pattern.compile("^https://");
    private String f11089b;
    private Bundle f11090c;
    private String f11091d;

    /* compiled from: vungle */
    public static abstract class C4258a<T extends tr> {
        protected abstract T m13953b();

        protected C4258a() {
        }

        protected T m13954c() {
            T b = m13953b();
            b.m13933a(new Bundle());
            return b;
        }
    }

    /* compiled from: vungle */
    public enum C4259b {
        GET,
        HEAD,
        POST
    }

    /* compiled from: vungle */
    public enum C4260c {
        download,
        reportAd,
        requestConfig,
        requestLocalAd,
        requestWillPlayAd,
        trackEvent,
        trackInstall,
        unfilledAd,
        reportExceptions,
        appFingerprint
    }

    protected abstract C4259b mo6959a();

    protected abstract C4260c mo6960b();

    protected tr() {
    }

    public final String m13937c() {
        return this.f11089b;
    }

    public final void m13934a(String str) {
        this.f11089b = str;
    }

    public final void m13933a(Bundle bundle) {
        this.f11090c = bundle;
    }

    public final Bundle m13938d() {
        return this.f11090c;
    }

    public final String m13936b(String str) {
        if (this.f11090c == null) {
            return null;
        }
        return this.f11090c.getString(str);
    }

    public final String m13939e() {
        return m13936b("Content-Encoding");
    }

    public final String m13940f() {
        return this.f11091d;
    }

    public String toString() {
        return "{" + mo6960b() + "}";
    }
}
