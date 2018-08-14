package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;

@zzadh
public final class zzjp extends zzlb {
    private final AppEventListener zzvo;

    public zzjp(AppEventListener appEventListener) {
        this.zzvo = appEventListener;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzvo;
    }

    public final void onAppEvent(String str, String str2) {
        this.zzvo.onAppEvent(str, str2);
    }
}
