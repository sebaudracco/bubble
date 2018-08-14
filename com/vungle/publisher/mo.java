package com.vungle.publisher;

import android.content.SharedPreferences;
import com.vungle.publisher.env.i;
import com.vungle.publisher.mj.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class mo implements MembersInjector<a> {
    static final /* synthetic */ boolean f10756a = (!mo.class.desiredAssertionStatus());
    private final Provider<qg> f10757b;
    private final Provider<i> f10758c;
    private final Provider<SharedPreferences> f10759d;
    private final Provider<bz> f10760e;

    public /* synthetic */ void injectMembers(Object obj) {
        m13705a((a) obj);
    }

    public mo(Provider<qg> provider, Provider<i> provider2, Provider<SharedPreferences> provider3, Provider<bz> provider4) {
        if (f10756a || provider != null) {
            this.f10757b = provider;
            if (f10756a || provider2 != null) {
                this.f10758c = provider2;
                if (f10756a || provider3 != null) {
                    this.f10759d = provider3;
                    if (f10756a || provider4 != null) {
                        this.f10760e = provider4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> m13704a(Provider<qg> provider, Provider<i> provider2, Provider<SharedPreferences> provider3, Provider<bz> provider4) {
        return new mo(provider, provider2, provider3, provider4);
    }

    public void m13705a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (qg) this.f10757b.get();
        aVar.b = (i) this.f10758c.get();
        aVar.c = (SharedPreferences) this.f10759d.get();
        aVar.d = (bz) this.f10760e.get();
    }
}
