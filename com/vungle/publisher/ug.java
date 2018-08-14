package com.vungle.publisher;

import com.vungle.publisher.tw.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ug implements MembersInjector<ue> {
    static final /* synthetic */ boolean f11137a = (!ug.class.desiredAssertionStatus());
    private final Provider<uh> f11138b;
    private final Provider<a> f11139c;
    private final Provider<ts.a> f11140d;

    public /* synthetic */ void injectMembers(Object obj) {
        m13974a((ue) obj);
    }

    public ug(Provider<uh> provider, Provider<a> provider2, Provider<ts.a> provider3) {
        if (f11137a || provider != null) {
            this.f11138b = provider;
            if (f11137a || provider2 != null) {
                this.f11139c = provider2;
                if (f11137a || provider3 != null) {
                    this.f11140d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ue> m13973a(Provider<uh> provider, Provider<a> provider2, Provider<ts.a> provider3) {
        return new ug(provider, provider2, provider3);
    }

    public void m13974a(ue ueVar) {
        if (ueVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ueVar.a = (uh) this.f11138b.get();
        ueVar.b = (a) this.f11139c.get();
        ueVar.c = (ts.a) this.f11140d.get();
    }
}
