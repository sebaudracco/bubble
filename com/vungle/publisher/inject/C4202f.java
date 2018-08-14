package com.vungle.publisher.inject;

import com.vungle.publisher.env.AndroidDevice;
import com.vungle.publisher.env.C4181i;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4202f implements Factory<C4181i> {
    static final /* synthetic */ boolean f10378a = (!C4202f.class.desiredAssertionStatus());
    private final a f10379b;
    private final Provider<AndroidDevice> f10380c;

    public /* synthetic */ Object get() {
        return m13382a();
    }

    public C4202f(a aVar, Provider<AndroidDevice> provider) {
        if (f10378a || aVar != null) {
            this.f10379b = aVar;
            if (f10378a || provider != null) {
                this.f10380c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public C4181i m13382a() {
        return (C4181i) Preconditions.checkNotNull(this.f10379b.a((AndroidDevice) this.f10380c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<C4181i> m13381a(a aVar, Provider<AndroidDevice> provider) {
        return new C4202f(aVar, provider);
    }
}
