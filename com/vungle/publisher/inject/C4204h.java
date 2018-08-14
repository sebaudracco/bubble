package com.vungle.publisher.inject;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: vungle */
public final class C4204h implements Factory<Class> {
    static final /* synthetic */ boolean f10384a = (!C4204h.class.desiredAssertionStatus());
    private final a f10385b;

    public /* synthetic */ Object get() {
        return m13386a();
    }

    public C4204h(a aVar) {
        if (f10384a || aVar != null) {
            this.f10385b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public Class m13386a() {
        return (Class) Preconditions.checkNotNull(this.f10385b.e(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Class> m13385a(a aVar) {
        return new C4204h(aVar);
    }
}
