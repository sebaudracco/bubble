package com.vungle.publisher.inject;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: vungle */
public final class C4199c implements Factory<Context> {
    static final /* synthetic */ boolean f10370a = (!C4199c.class.desiredAssertionStatus());
    private final a f10371b;

    public /* synthetic */ Object get() {
        return m13376a();
    }

    public C4199c(a aVar) {
        if (f10370a || aVar != null) {
            this.f10371b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public Context m13376a() {
        return (Context) Preconditions.checkNotNull(this.f10371b.b(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Context> m13375a(a aVar) {
        return new C4199c(aVar);
    }
}
