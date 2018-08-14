package com.vungle.publisher.inject;

import android.content.Context;
import com.vungle.publisher.env.C4179n;
import com.vungle.publisher.env.WrapperFramework;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4208l implements Factory<C4179n> {
    static final /* synthetic */ boolean f10394a = (!C4208l.class.desiredAssertionStatus());
    private final a f10395b;
    private final Provider<Context> f10396c;
    private final Provider<WrapperFramework> f10397d;

    public /* synthetic */ Object get() {
        return m13394a();
    }

    public C4208l(a aVar, Provider<Context> provider, Provider<WrapperFramework> provider2) {
        if (f10394a || aVar != null) {
            this.f10395b = aVar;
            if (f10394a || provider != null) {
                this.f10396c = provider;
                if (f10394a || provider2 != null) {
                    this.f10397d = provider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public C4179n m13394a() {
        return (C4179n) Preconditions.checkNotNull(this.f10395b.a((Context) this.f10396c.get(), (WrapperFramework) this.f10397d.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<C4179n> m13393a(a aVar, Provider<Context> provider, Provider<WrapperFramework> provider2) {
        return new C4208l(aVar, provider, provider2);
    }
}
