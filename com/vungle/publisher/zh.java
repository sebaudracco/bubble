package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.env.o;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class zh implements MembersInjector<zf> {
    static final /* synthetic */ boolean f11388a = (!zh.class.desiredAssertionStatus());
    private final Provider<Context> f11389b;
    private final Provider<o> f11390c;

    public /* synthetic */ void injectMembers(Object obj) {
        m14202a((zf) obj);
    }

    public zh(Provider<Context> provider, Provider<o> provider2) {
        if (f11388a || provider != null) {
            this.f11389b = provider;
            if (f11388a || provider2 != null) {
                this.f11390c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<zf> m14201a(Provider<Context> provider, Provider<o> provider2) {
        return new zh(provider, provider2);
    }

    public void m14202a(zf zfVar) {
        if (zfVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        zfVar.a = (Context) this.f11389b.get();
        zfVar.b = (o) this.f11390c.get();
    }
}
