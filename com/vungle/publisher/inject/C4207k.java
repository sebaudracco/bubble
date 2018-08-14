package com.vungle.publisher.inject;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4207k implements Factory<String> {
    static final /* synthetic */ boolean f10391a = (!C4207k.class.desiredAssertionStatus());
    private final a f10392b;
    private final Provider<Context> f10393c;

    public /* synthetic */ Object get() {
        return m13392a();
    }

    public C4207k(a aVar, Provider<Context> provider) {
        if (f10391a || aVar != null) {
            this.f10392b = aVar;
            if (f10391a || provider != null) {
                this.f10393c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public String m13392a() {
        return (String) Preconditions.checkNotNull(this.f10392b.b((Context) this.f10393c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<String> m13391a(a aVar, Provider<Context> provider) {
        return new C4207k(aVar, provider);
    }
}
