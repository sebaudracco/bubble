package com.vungle.publisher.inject;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: vungle */
public final class C4213q implements Factory<String> {
    static final /* synthetic */ boolean f10408a = (!C4213q.class.desiredAssertionStatus());
    private final a f10409b;

    public /* synthetic */ Object get() {
        return m13404a();
    }

    public C4213q(a aVar) {
        if (f10408a || aVar != null) {
            this.f10409b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public String m13404a() {
        return (String) Preconditions.checkNotNull(this.f10409b.g(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<String> m13403a(a aVar) {
        return new C4213q(aVar);
    }
}
