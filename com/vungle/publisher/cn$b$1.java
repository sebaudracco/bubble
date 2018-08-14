package com.vungle.publisher;

import com.vungle.publisher.cn.b;

/* compiled from: vungle */
class cn$b$1 extends q<F> {
    final /* synthetic */ m f9852a;
    final /* synthetic */ b f9853b;

    cn$b$1(b bVar, m mVar) {
        this.f9853b = bVar;
        this.f9852a = mVar;
    }

    protected /* synthetic */ Object m12934a() {
        return m12938e();
    }

    protected /* synthetic */ Object m12935b() {
        return m12941h();
    }

    protected /* synthetic */ Object m12936c() {
        return m12939f();
    }

    protected /* synthetic */ Object m12937d() {
        return m12940g();
    }

    protected F m12938e() {
        return this.f9853b.a;
    }

    protected F m12939f() {
        return this.f9853b.c;
    }

    protected F m12940g() {
        return this.f9853b.d;
    }

    protected F m12941h() {
        throw new IllegalArgumentException("cannot get cacheable streamingVideoAdReportFactory for ad type: " + this.f9852a);
    }
}
