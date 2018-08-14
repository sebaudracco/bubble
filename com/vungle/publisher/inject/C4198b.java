package com.vungle.publisher.inject;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4198b implements Factory<String> {
    static final /* synthetic */ boolean f10367a = (!C4198b.class.desiredAssertionStatus());
    private final a f10368b;
    private final Provider<Context> f10369c;

    public /* synthetic */ Object get() {
        return m13374a();
    }

    public C4198b(a aVar, Provider<Context> provider) {
        if (f10367a || aVar != null) {
            this.f10368b = aVar;
            if (f10367a || provider != null) {
                this.f10369c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public String m13374a() {
        return (String) Preconditions.checkNotNull(this.f10368b.a((Context) this.f10369c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<String> m13373a(a aVar, Provider<Context> provider) {
        return new C4198b(aVar, provider);
    }
}
