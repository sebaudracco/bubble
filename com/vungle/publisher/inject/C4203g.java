package com.vungle.publisher.inject;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4203g implements Factory<SharedPreferences> {
    static final /* synthetic */ boolean f10381a = (!C4203g.class.desiredAssertionStatus());
    private final a f10382b;
    private final Provider<Context> f10383c;

    public /* synthetic */ Object get() {
        return m13384a();
    }

    public C4203g(a aVar, Provider<Context> provider) {
        if (f10381a || aVar != null) {
            this.f10382b = aVar;
            if (f10381a || provider != null) {
                this.f10383c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public SharedPreferences m13384a() {
        return (SharedPreferences) Preconditions.checkNotNull(this.f10382b.e((Context) this.f10383c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SharedPreferences> m13383a(a aVar, Provider<Context> provider) {
        return new C4203g(aVar, provider);
    }
}
