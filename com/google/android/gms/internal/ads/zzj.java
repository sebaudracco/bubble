package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.concurrent.Executor;

final class zzj implements Executor {
    private final /* synthetic */ Handler val$handler;

    zzj(zzi com_google_android_gms_internal_ads_zzi, Handler handler) {
        this.val$handler = handler;
    }

    public final void execute(Runnable runnable) {
        this.val$handler.post(runnable);
    }
}
