package com.vungle.publisher.inject;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: vungle */
public final class C4210n implements Factory<Class> {
    static final /* synthetic */ boolean f10401a = (!C4210n.class.desiredAssertionStatus());
    private final a f10402b;

    public /* synthetic */ Object get() {
        return m13398a();
    }

    public C4210n(a aVar) {
        if (f10401a || aVar != null) {
            this.f10402b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public Class m13398a() {
        return (Class) Preconditions.checkNotNull(this.f10402b.c(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Class> m13397a(a aVar) {
        return new C4210n(aVar);
    }
}
