package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.mj.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class mp<W extends mj> implements MembersInjector<b<W>> {
    static final /* synthetic */ boolean f10761a = (!mp.class.desiredAssertionStatus());
    private final Provider<Context> f10762b;
    private final Provider<qg> f10763c;

    public /* synthetic */ void injectMembers(Object obj) {
        m13708a((b) obj);
    }

    public void m13708a(b<W> bVar) {
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.a = (Context) this.f10762b.get();
        bVar.b = (qg) this.f10763c.get();
    }

    public static <W extends mj> void m13706a(b<W> bVar, Provider<Context> provider) {
        bVar.a = (Context) provider.get();
    }

    public static <W extends mj> void m13707b(b<W> bVar, Provider<qg> provider) {
        bVar.b = (qg) provider.get();
    }
}
