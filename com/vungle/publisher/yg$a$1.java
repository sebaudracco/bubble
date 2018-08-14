package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import com.vungle.publisher.yg.a;
import org.json.JSONObject;
import rx.exceptions.Exceptions;

/* compiled from: vungle */
class yg$a$1 extends C4239q<wc> {
    final /* synthetic */ JSONObject f11340a;
    final /* synthetic */ a f11341b;

    yg$a$1(a aVar, JSONObject jSONObject) {
        this.f11341b = aVar;
        this.f11340a = jSONObject;
    }

    protected /* synthetic */ Object mo6946a() {
        return m14152e();
    }

    protected /* synthetic */ Object mo6947b() {
        return m14153f();
    }

    protected /* synthetic */ Object mo6948c() {
        return m14154g();
    }

    protected /* synthetic */ Object mo6949d() {
        return m14155h();
    }

    protected wc m14152e() {
        try {
            return this.f11341b.a.d(this.f11340a);
        } catch (Throwable e) {
            throw Exceptions.propagate(e);
        }
    }

    protected wc m14153f() {
        Logger.m13641i(Logger.PREPARE_TAG, "received invalid ad from server, tossing it and getting a new one");
        throw new RuntimeException("received invalid ad from server, tossing it and getting a new one");
    }

    protected wc m14154g() {
        try {
            return this.f11341b.c.d(this.f11340a);
        } catch (Throwable e) {
            throw Exceptions.propagate(e);
        }
    }

    protected wc m14155h() {
        try {
            return this.f11341b.b.d(this.f11340a);
        } catch (Throwable e) {
            throw Exceptions.propagate(e);
        }
    }
}
