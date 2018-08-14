package com.vungle.publisher.inject;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: vungle */
public final class C4205i implements Factory<Class> {
    static final /* synthetic */ boolean f10386a = (!C4205i.class.desiredAssertionStatus());
    private final a f10387b;

    public /* synthetic */ Object get() {
        return m13388a();
    }

    public C4205i(a aVar) {
        if (f10386a || aVar != null) {
            this.f10387b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public Class m13388a() {
        return (Class) Preconditions.checkNotNull(this.f10387b.d(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Class> m13387a(a aVar) {
        return new C4205i(aVar);
    }
}
