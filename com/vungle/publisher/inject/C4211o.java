package com.vungle.publisher.inject;

import android.content.Context;
import android.view.WindowManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4211o implements Factory<WindowManager> {
    static final /* synthetic */ boolean f10403a = (!C4211o.class.desiredAssertionStatus());
    private final a f10404b;
    private final Provider<Context> f10405c;

    public /* synthetic */ Object get() {
        return m13400a();
    }

    public C4211o(a aVar, Provider<Context> provider) {
        if (f10403a || aVar != null) {
            this.f10404b = aVar;
            if (f10403a || provider != null) {
                this.f10405c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public WindowManager m13400a() {
        return (WindowManager) Preconditions.checkNotNull(this.f10404b.g((Context) this.f10405c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<WindowManager> m13399a(a aVar, Provider<Context> provider) {
        return new C4211o(aVar, provider);
    }
}
