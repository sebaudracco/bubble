package com.vungle.publisher;

import com.vungle.publisher.dk.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dn implements MembersInjector<a> {
    static final /* synthetic */ boolean f9950a = (!dn.class.desiredAssertionStatus());
    private final Provider<ci> f9951b;
    private final Provider<m.a> f9952c;
    private final Provider<dk> f9953d;
    private final Provider<ge.a> f9954e;
    private final Provider<iz.a> f9955f;

    public /* synthetic */ void injectMembers(Object obj) {
        m13057a((a) obj);
    }

    public dn(Provider<ci> provider, Provider<m.a> provider2, Provider<dk> provider3, Provider<ge.a> provider4, Provider<iz.a> provider5) {
        if (f9950a || provider != null) {
            this.f9951b = provider;
            if (f9950a || provider2 != null) {
                this.f9952c = provider2;
                if (f9950a || provider3 != null) {
                    this.f9953d = provider3;
                    if (f9950a || provider4 != null) {
                        this.f9954e = provider4;
                        if (f9950a || provider5 != null) {
                            this.f9955f = provider5;
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

    public static MembersInjector<a> m13056a(Provider<ci> provider, Provider<m.a> provider2, Provider<dk> provider3, Provider<ge.a> provider4, Provider<iz.a> provider5) {
        return new dn(provider, provider2, provider3, provider4, provider5);
    }

    public void m13057a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (ci) this.f9951b.get();
        aVar.e = (m.a) this.f9952c.get();
        aVar.a = this.f9953d;
        aVar.b = (ge.a) this.f9954e.get();
        aVar.c = (iz.a) this.f9955f.get();
    }
}
