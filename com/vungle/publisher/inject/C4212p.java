package com.vungle.publisher.inject;

import com.vungle.publisher.env.WrapperFramework;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: vungle */
public final class C4212p implements Factory<WrapperFramework> {
    static final /* synthetic */ boolean f10406a = (!C4212p.class.desiredAssertionStatus());
    private final a f10407b;

    public /* synthetic */ Object get() {
        return m13402a();
    }

    public C4212p(a aVar) {
        if (f10406a || aVar != null) {
            this.f10407b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public WrapperFramework m13402a() {
        return (WrapperFramework) Preconditions.checkNotNull(this.f10407b.f(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<WrapperFramework> m13401a(a aVar) {
        return new C4212p(aVar);
    }
}
