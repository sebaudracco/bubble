package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

final class zznl implements Callable<Void> {
    private final /* synthetic */ Context val$context;

    zznl(Context context) {
        this.val$context = context;
    }

    public final /* synthetic */ Object call() throws Exception {
        zzkb.zzik().initialize(this.val$context);
        return null;
    }
}
