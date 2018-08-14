package com.integralads.avid.library.inmobi.p122b;

import com.integralads.avid.library.inmobi.session.internal.C3333a;
import com.integralads.avid.library.inmobi.session.internal.p129a.C3331a;

/* compiled from: AvidBaseListenerImpl */
public abstract class C3288a {
    private C3333a f8424a;
    private C3331a f8425b;

    public C3288a(C3333a c3333a, C3331a c3331a) {
        this.f8424a = c3333a;
        this.f8425b = c3331a;
    }

    public void m11192a() {
        this.f8424a = null;
        this.f8425b = null;
    }

    protected C3333a m11193b() {
        return this.f8424a;
    }

    protected C3331a m11194c() {
        return this.f8425b;
    }

    protected void m11195d() {
        if (this.f8424a == null) {
            throw new IllegalStateException("The AVID ad session is ended. Please ensure you are not recording events after the session has ended.");
        }
    }
}
