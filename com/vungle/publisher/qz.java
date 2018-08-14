package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.env.i;
import com.vungle.publisher.env.n;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class qz implements MembersInjector<qw> {
    static final /* synthetic */ boolean f10952a = (!qz.class.desiredAssertionStatus());
    private final Provider<Context> f10953b;
    private final Provider<n> f10954c;
    private final Provider<i> f10955d;
    private final Provider<WrapperFramework> f10956e;
    private final Provider<String> f10957f;

    public /* synthetic */ void injectMembers(Object obj) {
        m13855a((qw) obj);
    }

    public qz(Provider<Context> provider, Provider<n> provider2, Provider<i> provider3, Provider<WrapperFramework> provider4, Provider<String> provider5) {
        if (f10952a || provider != null) {
            this.f10953b = provider;
            if (f10952a || provider2 != null) {
                this.f10954c = provider2;
                if (f10952a || provider3 != null) {
                    this.f10955d = provider3;
                    if (f10952a || provider4 != null) {
                        this.f10956e = provider4;
                        if (f10952a || provider5 != null) {
                            this.f10957f = provider5;
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
        throw new AssertionError();
    }

    public static MembersInjector<qw> m13854a(Provider<Context> provider, Provider<n> provider2, Provider<i> provider3, Provider<WrapperFramework> provider4, Provider<String> provider5) {
        return new qz(provider, provider2, provider3, provider4, provider5);
    }

    public void m13855a(qw qwVar) {
        if (qwVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        qwVar.a = (Context) this.f10953b.get();
        qwVar.b = (n) this.f10954c.get();
        qwVar.c = (i) this.f10955d.get();
        qwVar.d = (WrapperFramework) this.f10956e.get();
        qwVar.e = (String) this.f10957f.get();
    }
}
