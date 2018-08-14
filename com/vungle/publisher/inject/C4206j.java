package com.vungle.publisher.inject;

import com.vungle.publisher.sv;
import com.vungle.publisher.sz;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4206j implements Factory<sz> {
    static final /* synthetic */ boolean f10388a = (!C4206j.class.desiredAssertionStatus());
    private final a f10389b;
    private final Provider<sv> f10390c;

    public /* synthetic */ Object get() {
        return m13390a();
    }

    public C4206j(a aVar, Provider<sv> provider) {
        if (f10388a || aVar != null) {
            this.f10389b = aVar;
            if (f10388a || provider != null) {
                this.f10390c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public sz m13390a() {
        return (sz) Preconditions.checkNotNull(this.f10389b.a((sv) this.f10390c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<sz> m13389a(a aVar, Provider<sv> provider) {
        return new C4206j(aVar, provider);
    }
}
