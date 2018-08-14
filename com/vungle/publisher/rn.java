package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.rf.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rn implements MembersInjector<a> {
    static final /* synthetic */ boolean f10981a = (!rn.class.desiredAssertionStatus());
    private final Provider<Context> f10982b;
    private final Provider<qg> f10983c;
    private final Provider<rg.a> f10984d;
    private final Provider<rd> f10985e;
    private final Provider<rr> f10986f;

    public /* synthetic */ void injectMembers(Object obj) {
        m13883a((a) obj);
    }

    public rn(Provider<Context> provider, Provider<qg> provider2, Provider<rg.a> provider3, Provider<rd> provider4, Provider<rr> provider5) {
        if (f10981a || provider != null) {
            this.f10982b = provider;
            if (f10981a || provider2 != null) {
                this.f10983c = provider2;
                if (f10981a || provider3 != null) {
                    this.f10984d = provider3;
                    if (f10981a || provider4 != null) {
                        this.f10985e = provider4;
                        if (f10981a || provider5 != null) {
                            this.f10986f = provider5;
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

    public static MembersInjector<a> m13882a(Provider<Context> provider, Provider<qg> provider2, Provider<rg.a> provider3, Provider<rd> provider4, Provider<rr> provider5) {
        return new rn(provider, provider2, provider3, provider4, provider5);
    }

    public void m13883a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        mp.m13706a(aVar, this.f10982b);
        mp.m13707b(aVar, this.f10983c);
        aVar.c = (rg.a) this.f10984d.get();
        aVar.d = this.f10985e;
        aVar.e = (rr) this.f10986f.get();
    }
}
