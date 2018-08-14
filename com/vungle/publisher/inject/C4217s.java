package com.vungle.publisher.inject;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: vungle */
public final class C4217s implements Factory<String> {
    static final /* synthetic */ boolean f10466a = (!C4217s.class.desiredAssertionStatus());
    private final EndpointModule f10467b;

    public /* synthetic */ Object get() {
        return m13426a();
    }

    public C4217s(EndpointModule endpointModule) {
        if (f10466a || endpointModule != null) {
            this.f10467b = endpointModule;
            return;
        }
        throw new AssertionError();
    }

    public String m13426a() {
        return (String) Preconditions.checkNotNull(this.f10467b.a(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<String> m13425a(EndpointModule endpointModule) {
        return new C4217s(endpointModule);
    }
}
