package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;

/* compiled from: NativeStrandRootContainerLayout */
final class ag extends NativeStrandContainerLayout {
    private WeakReference<ai> f7070a;

    ag(@NonNull Context context) {
        super(context);
    }

    void m9287a(@NonNull ai aiVar) {
        this.f7070a = new WeakReference(aiVar);
    }

    ai m9286a() {
        return (ai) this.f7070a.get();
    }
}
