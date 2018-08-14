package com.vungle.publisher.inject;

import android.content.Context;
import android.media.AudioManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4200d implements Factory<AudioManager> {
    static final /* synthetic */ boolean f10372a = (!C4200d.class.desiredAssertionStatus());
    private final a f10373b;
    private final Provider<Context> f10374c;

    public /* synthetic */ Object get() {
        return m13378a();
    }

    public C4200d(a aVar, Provider<Context> provider) {
        if (f10372a || aVar != null) {
            this.f10373b = aVar;
            if (f10372a || provider != null) {
                this.f10374c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public AudioManager m13378a() {
        return (AudioManager) Preconditions.checkNotNull(this.f10373b.c((Context) this.f10374c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AudioManager> m13377a(a aVar, Provider<Context> provider) {
        return new C4200d(aVar, provider);
    }
}
